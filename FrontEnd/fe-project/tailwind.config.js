/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js,jsx,ts,tsx}"],
  theme: {
    extend: {
      width: {
        'header': '430px',
      },
      height: {
        'header': '80px',
      }
    },
  },
  plugins: [],
}

