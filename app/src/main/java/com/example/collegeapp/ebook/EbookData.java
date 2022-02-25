package com.example.collegeapp.ebook;

public class EbookData {
    public String name,pdfUrl;

    public EbookData() {
    }

    public EbookData(String name, String pdfUrl) {
        this.name = name;
        this.pdfUrl = pdfUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
