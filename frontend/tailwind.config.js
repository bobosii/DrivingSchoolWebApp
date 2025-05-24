const { theme } = require('./src/styles/theme');

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: theme.colors,
      spacing: theme.spacing,
      screens: theme.breakpoints,
      boxShadow: theme.shadows,
      borderRadius: theme.borderRadius,
      fontSize: theme.fontSizes,
      fontWeight: theme.fontWeights,
      lineHeight: theme.lineHeights,
      letterSpacing: theme.letterSpacing,
      transitionTimingFunction: theme.transitions,
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography'),
    require('@tailwindcss/aspect-ratio'),
  ],
} 