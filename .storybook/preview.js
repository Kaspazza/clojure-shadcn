// Import the library's Tailwind stylesheet so all Tailwind/design-token
// classes are available inside the Storybook canvas.
import '../resources/css/app.css';
import './playground.css';

import { withThemeByClassName } from '@storybook/addon-themes';
import {
  Canvas,
  Controls,
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
    ...examples.map((story) => story.parameters?.playground
      ? createElement(
        'section',
        { key: story.id, className: 'docs-playground', 'aria-label': story.name },
        createElement(Subheading, null, story.name),
        createElement('p', { className: 'docs-playground-description' }, story.parameters.playground.description),
        createElement(
          'div',
          { className: 'docs-playground-layout' },
          createElement(
            'div',
            { className: 'docs-playground-preview' },
            createElement(Canvas, {
              of: story.moduleExport,
              // Live, inline args keep this preview synchronized with Controls.
              story: { inline: true, __forceInitialArgs: false },
              source: { __forceInitialArgs: false },
              layout: 'centered',
              withToolbar: false,
            }),
          ),
          createElement(
            'div',
            { className: 'docs-playground-controls' },
            createElement('h3', { className: 'docs-playground-controls-title' }, 'Customize'),
            createElement(Controls, { of: story.moduleExport }),
          ),
        ),
      )
      : createElement(DocsStory, {
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

/** @type {import('@storybook/react-vite').Preview} */
const preview = {
  parameters: {
    layout: 'padded',
    docs: {
      page: docsPage,
    },
    options: {
      storySort: {
        order: ['Home', 'Docs', 'Components', 'Chat'],
      },
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
