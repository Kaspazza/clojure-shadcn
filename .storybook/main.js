import { readFile } from 'node:fs/promises';
import { basename, dirname, join } from 'node:path';

const cljsStoryPattern = /cljs-stories\/.*_stories\.js$/;
const namedExportPattern = /export\s+(?:let|const|var|function)\s+(\w+)/g;
const titlePattern = /\.default\$\s*=\s*\(\{[^}]*"title":\s*"([^"]+)"/;
const componentTitlePattern = /^(?:Components|Chat)\//;

const storyRank = (exportName) => {
  if (exportName === 'Installation') return 0;
  if (exportName === 'ApiReference') return 1;
  return 2;
};

// shadow-cljs emits valid ESM stories, but their default metadata export is a
// runtime reference rather than the object literal expected by Storybook's CSF
// parser. Index the compiled exports directly and leave loading to Vite.
const cljsStoryIndexer = {
  test: cljsStoryPattern,
  async createIndex(fileName) {
    const moduleSource = await readFile(fileName, 'utf8');
    const runtimeFile = join(dirname(fileName), 'cljs-runtime', basename(fileName));
    const runtimeSource = await readFile(runtimeFile, 'utf8');
    const title = runtimeSource.match(titlePattern)?.[1];

    if (!title) {
      throw new Error(`Could not find Storybook title in ${runtimeFile}`);
    }

    const tags = componentTitlePattern.test(title) ? ['autodocs'] : [];
    const exports = [...moduleSource.matchAll(namedExportPattern)]
      .map(([, exportName], sourceOrder) => ({ exportName, sourceOrder }))
      .sort((left, right) =>
        storyRank(left.exportName) - storyRank(right.exportName)
        || left.sourceOrder - right.sourceOrder
      );

    return exports.map(({ exportName }) => ({
      type: 'story',
      title,
      importPath: fileName,
      exportName,
      tags,
    }));
  },
};

/** @type {import('@storybook/react-vite').StorybookConfig} */
const config = {
  stories: ['../cljs-stories/*_stories.js'],

  experimental_indexers: async (existingIndexers) => [
    cljsStoryIndexer,
    ...existingIndexers,
  ],

  framework: {
    name: '@storybook/react-vite',
    options: {},
  },

  typescript: {
    reactDocgen: false,
  },

  addons: ['@storybook/addon-docs']
};

export default config;
