/** @type {import('@storybook/react-vite').StorybookConfig} */
const config = {
  // Auto-generated ESM bridge files — created by the :build-hooks in
  // shadow-cljs.edn after every :stories compile. Each bridge imports
  // from native ESM output in cljs-stories/ and re-exports with the
  // literal syntax Storybook 10's static CSF indexer requires.
  //
  // Developers only write CLJS story namespaces — bridges are generated.
  stories: ['../stories/js/**/*.stories.js'],

  framework: {
    name: '@storybook/react-vite',
    options: {},
  },

  typescript: {
    reactDocgen: false,
  },
};

export default config;
