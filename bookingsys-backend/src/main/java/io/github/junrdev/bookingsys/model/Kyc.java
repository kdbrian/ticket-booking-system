package io.github.junrdev.bookingsys.model;

public class Kyc {

    private String identificationNumber;
    private String uploadedFormLink;

    private FormEvaluation formEvaluation = FormEvaluation.IN_PROGRESS;

    // Enum for Form Evaluation Status
    public enum FormEvaluation {
        DENIED,
        ACCEPTED,
        IN_PROGRESS,
        REFILL
    }

    public Kyc() {
    }

    public Kyc(String identificationNumber, String uploadedFormLink, FormEvaluation formEvaluation) {
        this.identificationNumber = identificationNumber;
        this.uploadedFormLink = uploadedFormLink;
        this.formEvaluation = formEvaluation;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getUploadedFormLink() {
        return uploadedFormLink;
    }

    public void setUploadedFormLink(String uploadedFormLink) {
        this.uploadedFormLink = uploadedFormLink;
    }

    public FormEvaluation getFormEvaluation() {
        return formEvaluation;
    }

    public void setFormEvaluation(FormEvaluation formEvaluation) {
        this.formEvaluation = formEvaluation;
    }

    @Override
    public String toString() {
        return "Kyc{" +
                "identificationNumber='" + identificationNumber + '\'' +
                ", uploadedFormLink='" + uploadedFormLink + '\'' +
                ", formEvaluation=" + formEvaluation +
                '}';
    }
}