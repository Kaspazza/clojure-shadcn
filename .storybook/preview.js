// Import the library's Tailwind stylesheet so all Tailwind/design-token
// classes are available inside the Storybook canvas.
import '../resources/css/app.css';

import { withThemeByClassName } from '@storybook/addon-themes';

/** @type {import('@storybook/react').Preview} */
const preview = {
  parameters: {
    layout: 'padded',
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
