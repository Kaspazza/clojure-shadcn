import { addons } from 'storybook/manager-api';
import { create } from 'storybook/theming';

// Brand the Storybook manager (sidebar/toolbar chrome) to match the
// library's design tokens. Canvas styling lives in preview.js.
addons.setConfig({
  theme: create({
    base: 'light',
    brandTitle: 'clojure-shadcn',
    brandUrl: 'https://github.com/kaspazza/clojure-shadcn',
    brandTarget: '_blank',
    // oklch(0.6270 0.2650 303.9) — the library's --primary token
    colorPrimary: '#8b5cf6',
    colorSecondary: '#8b5cf6',
    appBg: '#f7f7f5',
    appContentBg: '#ffffff',
    appPreviewBg: '#ffffff',
  }),
});
