import { Webapp2Page } from './app.po';

describe('webapp2 App', function() {
  let page: Webapp2Page;

  beforeEach(() => {
    page = new Webapp2Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
