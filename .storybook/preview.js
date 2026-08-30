// Import the library's Tailwind stylesheet so all Tailwind/design-token
// classes are available inside the Storybook canvas.
import '../resources/css/app.css';

import { withThemeByClassName } from '@storybook/addon-themes';
import {
  Description,
  DocsContext,
  DocsStory,
  Story,
  Subheading,
  Title,
} from '@storybook/addon-docs/blocks';
import { Fragment, createElement, useContext } from 'react';

const storyExportName = (story) => story.parameters?.__namedExportOrder
  ?? story.exportName;

const DocsSections = () => {
  const { componentStories } = useContext(DocsContext);
  const stories = componentStories();
  const installation = stories.find((story) => storyExportName(story) === 'Installation');
  const apiReference = stories.find((story) => storyExportName(story) === 'ApiReference');
  const examples = stories.filter((story) => !['Installation', 'ApiReference'].includes(storyExportName(story)));

  const section = (title, story) => story && createElement(
    Fragment,
    { key: story.id },
    createElement(Subheading, null, title),
    createElement(Story, {
      of: story.moduleExport,
      __forceInitialArgs: true,
    }),
  );

  return createElement(
    Fragment,
    null,
    section('Installation', installation),
    section('API reference', apiReference),
    examples.length > 0 && createElement(Subheading, null, 'Examples'),
    ...examples.map((story) => createElement(DocsStory, {
      key: story.id,
      of: story.moduleExport,
      expanded: true,
      __forceInitialArgs: true,
    })),
  );
};

const docsPage = () => createElement(
  Fragment,
  null,
  createElement(Title),
  createElement(Description),
  createElement(DocsSections),
);

/** @type {import('@storybook/react').Preview} */
const preview = {
  parameters: {
    layout: 'padded',
    docs: {
      page: docsPage,
    },
  },

  // Toolbar dark/light toggle — toggles the `dark` class on the preview
  // iframe's <html>, which drives the .dark CSS variable overrides.
  decorators: [
    withThemeByClassName({
      themes: {
        light: '',
        dark: 'dark',
      },
      defaultTheme: 'light',
    }),
  ],
};

export default preview;
