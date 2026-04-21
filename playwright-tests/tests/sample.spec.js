const { test, expect } = require('@playwright/test');

test('Sample test - verify page title', async ({ page }) => {
  await page.goto('https://www.google.com');
  await expect(page).toHaveTitle(/Google/);
  console.log('Page title verified successfully!');
});
