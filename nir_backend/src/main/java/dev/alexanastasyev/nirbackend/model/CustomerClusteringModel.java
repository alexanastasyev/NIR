package dev.alexanastasyev.nirbackend.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerClusteringModel {

    /**
     * Идентификатор
     */
    private long id;

    /**
     * Год рождения
     */
    private double birthYear;

    /**
     * Образование
     */
    private double education;

    /**
     * Семейный статус
     */
    private double maritalStatus;

    /**
     * Доход
     */
    private double income;

    /**
     * Количество детей
     */
    private double childrenAmount;

    /**
     * Дата привлечения в компанию
     */
    private double enrollmentDate;

    /**
     * Количество дней с момента последней покупки
     */
    private double recency;

    /**
     * Есть ли жалобы за последние 2 года
     */
    private double complains;

    /**
     * Потрачено на вино за последние 2 года
     */
    private double wineAmount;

    /**
     * Потрачено на фрукты за последние 2 года
     */
    private double fruitsAmount;

    /**
     * Потрачено на мясо за последние 2 года
     */
    private double meatAmount;

    /**
     * Потрачено на рыбу за последние 2 года
     */
    private double fishAmount;

    /**
     * Потрачено на сладкое за последние 2 года
     */
    private double sweetAmount;

    /**
     * Потрачено на золото за последние 2 года
     */
    private double goldAmount;

    /**
     * Покупок по скидкам
     */
    private double discountPurchasesAmount;

    /**
     * Количество принятых рекламных кампаний
     */
    private double acceptedCampaignsAmount;

    /**
     * Количество покупок через сайт
     */
    private double webPurchasesAmount;

    /**
     * Количество покупок с помощью каталога
     */
    private double catalogPurchasesAmount;

    /**
     * Количество покупок непосредственно в магазине
     */
    private double storePurchasesAmount;

    /**
     * Посещений веб-сайта за последний месяц
     */
    private double websiteVisitsAmount;

    public CustomerClusteringModel(CustomerCSVModel csvModel) {

        this.id = Integer.parseInt(csvModel.getId());

        this.birthYear = Double.parseDouble(csvModel.getBirthYear());

        if ("Graduation".equals(csvModel.getEducation())) {
            this.education = 0.5;
        } else if ("Basic".equals(csvModel.getEducation()) || "2n Cycle".equals(csvModel.getEducation())) {
            this.education = 0;
        } else if ("Master".equals(csvModel.getEducation()) || "PhD".equals(csvModel.getEducation())) {
            this.education = 1;
        }

        if ("YOLO".equals(csvModel.getMaritalStatus()) || "Absurd".equals(csvModel.getMaritalStatus())
                || "Alone".equals(csvModel.getMaritalStatus()) || "Widow".equals(csvModel.getMaritalStatus())
                || "Divorced".equals(csvModel.getMaritalStatus())) {
            this.maritalStatus = 0;
        } else if ("Together".equals(csvModel.getMaritalStatus()) || "Married".equals(csvModel.getMaritalStatus())) {
            this.maritalStatus = 1;
        }

        this.income = Double.parseDouble(csvModel.getIncome());

        this.childrenAmount = Double.parseDouble(csvModel.getKidsAmount()) + Double.parseDouble(csvModel.getTeensAmount());

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(csvModel.getEnrollmentDate());
            this.enrollmentDate = parsedDate.getTime();
        } catch (Exception e) {
            this.enrollmentDate = 0.5; // Ситуация никогда не должна произойти
        }

        this.recency = Double.parseDouble(csvModel.getRecency());

        this.complains = Double.parseDouble(csvModel.getComplains());

        this.wineAmount = Double.parseDouble(csvModel.getWineAmount());

        this.fruitsAmount = Double.parseDouble(csvModel.getFruitsAmount());

        this.meatAmount = Double.parseDouble(csvModel.getMeatAmount());

        this.fishAmount = Double.parseDouble(csvModel.getFishAmount());

        this.sweetAmount = Double.parseDouble(csvModel.getSweetAmount());

        this.goldAmount = Double.parseDouble(csvModel.getGoldAmount());

        this.discountPurchasesAmount = Double.parseDouble(csvModel.getDiscountPurchasesAmount());

        this.acceptedCampaignsAmount = Double.parseDouble(csvModel.getAcceptedCampaign1())
                + Double.parseDouble(csvModel.getAcceptedCampaign2())
                + Double.parseDouble(csvModel.getAcceptedCampaign3())
                + Double.parseDouble(csvModel.getAcceptedCampaign4())
                + Double.parseDouble(csvModel.getAcceptedCampaign5())
                + Double.parseDouble(csvModel.getResponse());

        this.webPurchasesAmount = Double.parseDouble(csvModel.getWebPurchasesAmount());

        this.catalogPurchasesAmount = Double.parseDouble(csvModel.getCatalogPurchasesAmount());

        this.storePurchasesAmount = Double.parseDouble(csvModel.getStorePurchasesAmount());

        this.websiteVisitsAmount = Double.parseDouble(csvModel.getVisitsAmount());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(double birthYear) {
        this.birthYear = birthYear;
    }

    public double getEducation() {
        return education;
    }

    public void setEducation(double education) {
        this.education = education;
    }

    public double getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(double maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getChildrenAmount() {
        return childrenAmount;
    }

    public void setChildrenAmount(double childrenAmount) {
        this.childrenAmount = childrenAmount;
    }

    public double getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(double enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public double getRecency() {
        return recency;
    }

    public void setRecency(double recency) {
        this.recency = recency;
    }

    public double getComplains() {
        return complains;
    }

    public void setComplains(double complains) {
        this.complains = complains;
    }

    public double getWineAmount() {
        return wineAmount;
    }

    public void setWineAmount(double wineAmount) {
        this.wineAmount = wineAmount;
    }

    public double getFruitsAmount() {
        return fruitsAmount;
    }

    public void setFruitsAmount(double fruitsAmount) {
        this.fruitsAmount = fruitsAmount;
    }

    public double getMeatAmount() {
        return meatAmount;
    }

    public void setMeatAmount(double meatAmount) {
        this.meatAmount = meatAmount;
    }

    public double getFishAmount() {
        return fishAmount;
    }

    public void setFishAmount(double fishAmount) {
        this.fishAmount = fishAmount;
    }

    public double getSweetAmount() {
        return sweetAmount;
    }

    public void setSweetAmount(double sweetAmount) {
        this.sweetAmount = sweetAmount;
    }

    public double getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(double goldAmount) {
        this.goldAmount = goldAmount;
    }

    public double getDiscountPurchasesAmount() {
        return discountPurchasesAmount;
    }

    public void setDiscountPurchasesAmount(double discountPurchasesAmount) {
        this.discountPurchasesAmount = discountPurchasesAmount;
    }

    public double getAcceptedCampaignsAmount() {
        return acceptedCampaignsAmount;
    }

    public void setAcceptedCampaignsAmount(double acceptedCampaignsAmount) {
        this.acceptedCampaignsAmount = acceptedCampaignsAmount;
    }

    public double getWebPurchasesAmount() {
        return webPurchasesAmount;
    }

    public void setWebPurchasesAmount(double webPurchasesAmount) {
        this.webPurchasesAmount = webPurchasesAmount;
    }

    public double getCatalogPurchasesAmount() {
        return catalogPurchasesAmount;
    }

    public void setCatalogPurchasesAmount(double catalogPurchasesAmount) {
        this.catalogPurchasesAmount = catalogPurchasesAmount;
    }

    public double getStorePurchasesAmount() {
        return storePurchasesAmount;
    }

    public void setStorePurchasesAmount(double storePurchasesAmount) {
        this.storePurchasesAmount = storePurchasesAmount;
    }

    public double getWebsiteVisitsAmount() {
        return websiteVisitsAmount;
    }

    public void setWebsiteVisitsAmount(double websiteVisitsAmount) {
        this.websiteVisitsAmount = websiteVisitsAmount;
    }

    @Override
    public String toString() {
        return "CustomerClusteringModel{" +
                "id=" + id +
                ", birthYear=" + birthYear +
                ", education=" + education +
                ", maritalStatus=" + maritalStatus +
                ", income=" + income +
                ", childrenAmount=" + childrenAmount +
                ", enrollmentDate=" + enrollmentDate +
                ", recency=" + recency +
                ", complains=" + complains +
                ", wineAmount=" + wineAmount +
                ", fruitsAmount=" + fruitsAmount +
                ", meatAmount=" + meatAmount +
                ", fishAmount=" + fishAmount +
                ", sweetAmount=" + sweetAmount +
                ", goldAmount=" + goldAmount +
                ", discountPurchasesAmount=" + discountPurchasesAmount +
                ", acceptedCampaignsAmount=" + acceptedCampaignsAmount +
                ", webPurchasesAmount=" + webPurchasesAmount +
                ", catalogPurchasesAmount=" + catalogPurchasesAmount +
                ", storePurchasesAmount=" + storePurchasesAmount +
                ", websiteVisitsAmount=" + websiteVisitsAmount +
                '}';
    }
}
