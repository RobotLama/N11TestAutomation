package models.pages;

import methods.Methods;
import models.banners.CookieBanner;
import models.headers.BaseHeader;

public class HomePage {

    Methods methods;
    BaseHeader baseHeader;
    CookieBanner cookieBanner;

    public HomePage() {
        this.methods = new Methods();
        this.baseHeader = new BaseHeader();
        this.cookieBanner = new CookieBanner();
    }

    public void rejectCookies() {
        cookieBanner.rejectCookies();
    }

    public void search(String keyword) {
        baseHeader.searchProduct(keyword);
    }
}
