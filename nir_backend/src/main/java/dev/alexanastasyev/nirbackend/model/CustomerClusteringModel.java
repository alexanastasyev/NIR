package dev.alexanastasyev.nirbackend.model;

import dev.alexanastasyev.nirbackend.util.lambda.DoubleGetter;
import dev.alexanastasyev.nirbackend.util.lambda.DoubleSetter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CustomerClusteringModel {

    private static final int EMPTY_VALUE = -1;

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

    public CustomerClusteringModel() {
    }

    public CustomerClusteringModel(CustomerCSVModel csvModel) {
        extractId(csvModel.getId());
        extractBirthYear(csvModel.getBirthYear());
        extractEducation(csvModel.getEducation());
        extractMaritalStatus(csvModel.getMaritalStatus());
        extractIncome(csvModel.getIncome());
        extractChildrenAmount(csvModel.getKidsAmount(), csvModel.getTeensAmount());
        extractEnrollmentDate(csvModel.getEnrollmentDate());
        extractRecency(csvModel.getRecency());
        extractComplains(csvModel.getComplains());
        extractWineAmount(csvModel.getWineAmount());
        extractFruitsAmount(csvModel.getFruitsAmount());
        extractMeatAmount(csvModel.getMeatAmount());
        extractFishAmount(csvModel.getFishAmount());
        extractSweetAmount(csvModel.getSweetAmount());
        extractGoldAmount(csvModel.getGoldAmount());
        extractDiscountPurchasesAmount(csvModel.getDiscountPurchasesAmount());
        extractAcceptedCampaignsAmount(csvModel.getAcceptedCampaign1(), csvModel.getAcceptedCampaign2(),
                csvModel.getAcceptedCampaign3(), csvModel.getAcceptedCampaign4(), csvModel.getAcceptedCampaign5(),
                csvModel.getResponse());
        extractWebPurchasesAmount(csvModel.getWebPurchasesAmount());
        extractCatalogPurchasesAmount(csvModel.getCatalogPurchasesAmount());
        extractStorePurchasesAmount(csvModel.getStorePurchasesAmount());
        extractWebsiteVisitsAmount(csvModel.getVisitsAmount());
    }

    public CustomerClusteringModel(CustomerClusteringModel clusteringModel) {
        this.id = clusteringModel.getId();
        this.birthYear = clusteringModel.getBirthYear();
        this.education = clusteringModel.getEducation();
        this.maritalStatus = clusteringModel.getMaritalStatus();
        this.income = clusteringModel.getIncome();
        this.childrenAmount = clusteringModel.getChildrenAmount();
        this.enrollmentDate = clusteringModel.getEnrollmentDate();
        this.recency = clusteringModel.getRecency();
        this.complains = clusteringModel.getComplains();
        this.wineAmount = clusteringModel.getWineAmount();
        this.fruitsAmount = clusteringModel.getFruitsAmount();
        this.meatAmount = clusteringModel.getMeatAmount();
        this.fishAmount = clusteringModel.getFishAmount();
        this.sweetAmount = clusteringModel.getSweetAmount();
        this.goldAmount = clusteringModel.getGoldAmount();
        this.discountPurchasesAmount = clusteringModel.getDiscountPurchasesAmount();
        this.acceptedCampaignsAmount = clusteringModel.getAcceptedCampaignsAmount();
        this.webPurchasesAmount = clusteringModel.getWebPurchasesAmount();
        this.catalogPurchasesAmount = clusteringModel.getCatalogPurchasesAmount();
        this.storePurchasesAmount = clusteringModel.getStorePurchasesAmount();
        this.websiteVisitsAmount = clusteringModel.getWebsiteVisitsAmount();
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerClusteringModel that = (CustomerClusteringModel) o;
        return id == that.id && Double.compare(that.birthYear, birthYear) == 0 && Double.compare(that.education, education) == 0 && Double.compare(that.maritalStatus, maritalStatus) == 0 && Double.compare(that.income, income) == 0 && Double.compare(that.childrenAmount, childrenAmount) == 0 && Double.compare(that.enrollmentDate, enrollmentDate) == 0 && Double.compare(that.recency, recency) == 0 && Double.compare(that.complains, complains) == 0 && Double.compare(that.wineAmount, wineAmount) == 0 && Double.compare(that.fruitsAmount, fruitsAmount) == 0 && Double.compare(that.meatAmount, meatAmount) == 0 && Double.compare(that.fishAmount, fishAmount) == 0 && Double.compare(that.sweetAmount, sweetAmount) == 0 && Double.compare(that.goldAmount, goldAmount) == 0 && Double.compare(that.discountPurchasesAmount, discountPurchasesAmount) == 0 && Double.compare(that.acceptedCampaignsAmount, acceptedCampaignsAmount) == 0 && Double.compare(that.webPurchasesAmount, webPurchasesAmount) == 0 && Double.compare(that.catalogPurchasesAmount, catalogPurchasesAmount) == 0 && Double.compare(that.storePurchasesAmount, storePurchasesAmount) == 0 && Double.compare(that.websiteVisitsAmount, websiteVisitsAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthYear, education, maritalStatus, income, childrenAmount, enrollmentDate, recency, complains, wineAmount, fruitsAmount, meatAmount, fishAmount, sweetAmount, goldAmount, discountPurchasesAmount, acceptedCampaignsAmount, webPurchasesAmount, catalogPurchasesAmount, storePurchasesAmount, websiteVisitsAmount);
    }

    public CustomerClusteringModel copy() {
        return new CustomerClusteringModel(this);
    }

    public void replaceEmptyFieldsWithAverage(CustomerClusteringModel average) {
        replaceIfEmpty(this::getBirthYear, this::setBirthYear, average.getBirthYear());
        replaceIfEmpty(this::getEducation, this::setEducation, average.getEducation());
        replaceIfEmpty(this::getMaritalStatus, this::setMaritalStatus, average.getMaritalStatus());
        replaceIfEmpty(this::getIncome, this::setIncome, average.getIncome());
        replaceIfEmpty(this::getChildrenAmount, this::setChildrenAmount, average.getChildrenAmount());
        replaceIfEmpty(this::getEnrollmentDate, this::setEnrollmentDate, average.getEnrollmentDate());
        replaceIfEmpty(this::getRecency, this::setRecency, average.getRecency());
        replaceIfEmpty(this::getComplains, this::setComplains, average.getComplains());
        replaceIfEmpty(this::getWineAmount, this::setWineAmount, average.getWineAmount());
        replaceIfEmpty(this::getFruitsAmount, this::setFruitsAmount, average.getFruitsAmount());
        replaceIfEmpty(this::getMeatAmount, this::setMeatAmount, average.getMeatAmount());
        replaceIfEmpty(this::getFishAmount, this::setFishAmount, average.getFishAmount());
        replaceIfEmpty(this::getSweetAmount, this::setSweetAmount, average.getSweetAmount());
        replaceIfEmpty(this::getGoldAmount, this::setGoldAmount, average.getGoldAmount());
        replaceIfEmpty(this::getDiscountPurchasesAmount, this::setDiscountPurchasesAmount, average.getDiscountPurchasesAmount());
        replaceIfEmpty(this::getAcceptedCampaignsAmount, this::setAcceptedCampaignsAmount, average.getAcceptedCampaignsAmount());
        replaceIfEmpty(this::getWebPurchasesAmount, this::setWebPurchasesAmount, average.getWebPurchasesAmount());
        replaceIfEmpty(this::getCatalogPurchasesAmount, this::setCatalogPurchasesAmount, average.getCatalogPurchasesAmount());
        replaceIfEmpty(this::getStorePurchasesAmount, this::setStorePurchasesAmount, average.getStorePurchasesAmount());
        replaceIfEmpty(this::getWebsiteVisitsAmount, this::setWebsiteVisitsAmount, average.getWebsiteVisitsAmount());
    }

    private void replaceIfEmpty(DoubleGetter getter, DoubleSetter setter, double value) {
        if (getter.getValue() == EMPTY_VALUE) {
            setter.setValue(value);
        }
    }

    private void extractId(String id) {
        if (id == null || id.isEmpty()) {
            this.id = EMPTY_VALUE;
        } else {
            this.id = Integer.parseInt(id);
        }
    }

    private void extractBirthYear(String birthYear) {
        extractDoubleValue(birthYear, this::setBirthYear);
    }

    private void extractEducation(String education) {
        if ("Graduation".equals(education)) {
            this.education = 0.5;
        } else if ("Basic".equals(education) || "2n Cycle".equals(education)) {
            this.education = 0;
        } else if ("Master".equals(education) || "PhD".equals(education)) {
            this.education = 1;
        } else {
            this.education = EMPTY_VALUE;
        }
    }

    private void extractMaritalStatus(String maritalStatus) {
        if ("YOLO".equals(maritalStatus) || "Absurd".equals(maritalStatus)
                || "Alone".equals(maritalStatus) || "Widow".equals(maritalStatus)
                || "Divorced".equals(maritalStatus)) {
            this.maritalStatus = 0;
        } else if ("Together".equals(maritalStatus) || "Married".equals(maritalStatus)) {
            this.maritalStatus = 1;
        } else {
            this.maritalStatus = EMPTY_VALUE;
        }
    }

    private void extractIncome(String income) {
        extractDoubleValue(income, this::setIncome);
    }

    private void extractChildrenAmount(String kidsAmount, String teensAmount) {
        if (kidsAmount == null || kidsAmount.isEmpty() || teensAmount == null || teensAmount.isEmpty()) {
            this.childrenAmount = EMPTY_VALUE;
        } else {
            this.childrenAmount = Double.parseDouble(kidsAmount) + Double.parseDouble(teensAmount);
        }
    }

    private void extractEnrollmentDate(String enrollmentDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(enrollmentDate);
            this.enrollmentDate = parsedDate.getTime();
        } catch (Exception e) {
            this.enrollmentDate = EMPTY_VALUE;
        }
    }

    private void extractRecency(String recency) {
        extractDoubleValue(recency, this::setRecency);
    }

    private void extractComplains(String complains) {
        extractDoubleValue(complains, this::setComplains);
    }

    private void extractWineAmount(String wineAmount) {
        extractDoubleValue(wineAmount, this::setWineAmount);
    }

    private void extractFruitsAmount(String fruitsAmount) {
        extractDoubleValue(fruitsAmount, this::setFruitsAmount);
    }

    private void extractMeatAmount(String meatAmount) {
        extractDoubleValue(meatAmount, this::setMeatAmount);
    }

    private void extractFishAmount(String fishAmount) {
        extractDoubleValue(fishAmount, this::setFishAmount);
    }

    private void extractSweetAmount(String sweetAmount) {
        extractDoubleValue(sweetAmount, this::setSweetAmount);
    }

    private void extractGoldAmount(String goldAmount) {
        extractDoubleValue(goldAmount, this::setGoldAmount);
    }

    private void extractDiscountPurchasesAmount(String discountPurchasesAmount) {
        extractDoubleValue(discountPurchasesAmount, this::setDiscountPurchasesAmount);
    }

    private void extractAcceptedCampaignsAmount(String... acceptedCampaigns) {
        boolean valid = true;
        for (String campaign : acceptedCampaigns) {
            if (campaign == null || campaign.isEmpty()) {
                valid = false;
                break;
            }
        }
        if (valid) {
            double result = 0;
            for (String campaign : acceptedCampaigns) {
                result += Double.parseDouble(campaign);
            }
            this.acceptedCampaignsAmount = result;
        } else {
            this.acceptedCampaignsAmount = EMPTY_VALUE;
        }
    }

    private void extractWebPurchasesAmount(String webPurchasesAmount) {
        extractDoubleValue(webPurchasesAmount, this::setWebPurchasesAmount);
    }

    private void extractCatalogPurchasesAmount(String catalogPurchasesAmount) {
        extractDoubleValue(catalogPurchasesAmount, this::setCatalogPurchasesAmount);
    }

    private void extractStorePurchasesAmount(String storePurchasesAmount) {
        extractDoubleValue(storePurchasesAmount, this::setStorePurchasesAmount);
    }

    private void extractWebsiteVisitsAmount(String websiteVisitsAmount) {
        extractDoubleValue(websiteVisitsAmount, this::setWebsiteVisitsAmount);
    }

    private void extractDoubleValue(String value, DoubleSetter setter) {
        if (value == null || value.isEmpty()) {
            setter.setValue(EMPTY_VALUE);
        } else {
            setter.setValue(Double.parseDouble(value));
        }
    }
}
