/** @type {import('@storybook/react-vite').StorybookConfig} */
const config = {
  // Auto-generated ESM bridge files — created by the :build-hooks in
  // shadow-cljs.edn after every :stories compile.  Each bridge imports
  // from the CJS output in cljs-stories/ (transformed to ESM at serve
  // time by cljsStoriesPlugin in vite.config.js) and re-exports with
  // the literal syntax Storybook 10's static CSF indexer requires.
  //
  // Developers only write CLJS story namespaces — bridges are generated.
  stories: ['../stories/js/**/*.stories.js'],

  framework: {
    name: '@storybook/react-vite',
    options: {},
  },
};

export default config;
