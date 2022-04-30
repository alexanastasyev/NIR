package dev.alexanastasyev.nirbackend.model;

import com.opencsv.bean.CsvBindByPosition;

import java.lang.reflect.Field;

public class CustomerCSVModel {

    /**
     * ID покупателя
     */
    @CsvBindByPosition(position = 0)
    private String id;

    /**
     * Год рождения
     */
    @CsvBindByPosition(position = 1)
    private String birthYear;

    /**
     * Образование
     */
    @CsvBindByPosition(position = 2)
    private String education;

    /**
     * Семейный статус
     */
    @CsvBindByPosition(position = 3)
    private String maritalStatus;

    /**
     * Доход
     */
    @CsvBindByPosition(position = 4)
    private String income;

    /**
     * Количество маленьких детей
     */
    @CsvBindByPosition(position = 5)
    private String kidsAmount;

    /**
     * Количество детей-подростков
     */
    @CsvBindByPosition(position = 6)
    private String teensAmount;

    /**
     * Дата привлечения в компанию
     */
    @CsvBindByPosition(position = 7)
    private String enrollmentDate;

    /**
     * Количество дней с момента последней покупки
     */
    @CsvBindByPosition(position = 8)
    private String recency;

    /**
     * Есть ли жалобы за последние 2 года
     */
    @CsvBindByPosition(position = 25)
    private String complains;

    /**
     * Потрачено на вино за последние 2 года
     */
    @CsvBindByPosition(position = 9)
    private String wineAmount;

    /**
     * Потрачено на фрукты за последние 2 года
     */
    @CsvBindByPosition(position = 10)
    private String fruitsAmount;

    /**
     * Потрачено на мясо за последние 2 года
     */
    @CsvBindByPosition(position = 11)
    private String meatAmount;

    /**
     * Потрачено на рыбу за последние 2 года
     */
    @CsvBindByPosition(position = 12)
    private String fishAmount;

    /**
     * Потрачено на сладкое за последние 2 года
     */
    @CsvBindByPosition(position = 13)
    private String sweetAmount;

    /**
     * Потрачено на золото за последние 2 года
     */
    @CsvBindByPosition(position = 14)
    private String goldAmount;

    /**
     * Количество покупок по скидкам
     */
    @CsvBindByPosition(position = 15)
    private String discountPurchasesAmount;

    /**
     * Участвовал ли в рекламной компании №1
     */
    @CsvBindByPosition(position = 23)
    private String acceptedCampaign1;

    /**
     * Участвовал ли в рекламной компании №2
     */
    @CsvBindByPosition(position = 24)
    private String acceptedCampaign2;

    /**
     * Участвовал ли в рекламной компании №3
     */@CsvBindByPosition(position = 20)
    private String acceptedCampaign3;

    /**
     * Участвовал ли в рекламной компании №4
     */
    @CsvBindByPosition(position = 21)
    private String acceptedCampaign4;

    /**
     * Участвовал ли в рекламной компании №5
     */
    @CsvBindByPosition(position = 22)
    private String acceptedCampaign5;

    /**
     * Участвовал ли в последней рекламной компании
     */
    @CsvBindByPosition(position = 28)
    private String response;

    /**
     * Количество покупок через веб-сайт
     */
    @CsvBindByPosition(position = 16)
    private String webPurchasesAmount;

    /**
     * Количество покупок с использованием каталога
     */
    @CsvBindByPosition(position = 17)
    private String catalogPurchasesAmount;

    /**
     * Количество покупок непосредственно в магазине
     */
    @CsvBindByPosition(position = 18)
    private String storePurchasesAmount;

    /**
     * Количество посещений веб-сайта за послдений месяц
     */
    @CsvBindByPosition(position = 19)
    private String visitsAmount;

    public boolean hasNoEmptyFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (((String) field.get(this)).isEmpty()) {
                    return false;
                }
            } catch (IllegalAccessException exception) {
                return false;
            }
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getKidsAmount() {
        return kidsAmount;
    }

    public void setKidsAmount(String kidsAmount) {
        this.kidsAmount = kidsAmount;
    }

    public String getTeensAmount() {
        return teensAmount;
    }

    public void setTeensAmount(String teensAmount) {
        this.teensAmount = teensAmount;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getRecency() {
        return recency;
    }

    public void setRecency(String recency) {
        this.recency = recency;
    }

    public String getComplains() {
        return complains;
    }

    public void setComplains(String complains) {
        this.complains = complains;
    }

    public String getWineAmount() {
        return wineAmount;
    }

    public void setWineAmount(String wineAmount) {
        this.wineAmount = wineAmount;
    }

    public String getFruitsAmount() {
        return fruitsAmount;
    }

    public void setFruitsAmount(String fruitsAmount) {
        this.fruitsAmount = fruitsAmount;
    }

    public String getMeatAmount() {
        return meatAmount;
    }

    public void setMeatAmount(String meatAmount) {
        this.meatAmount = meatAmount;
    }

    public String getFishAmount() {
        return fishAmount;
    }

    public void setFishAmount(String fishAmount) {
        this.fishAmount = fishAmount;
    }

    public String getSweetAmount() {
        return sweetAmount;
    }

    public void setSweetAmount(String sweetAmount) {
        this.sweetAmount = sweetAmount;
    }

    public String getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(String goldAmount) {
        this.goldAmount = goldAmount;
    }

    public String getDiscountPurchasesAmount() {
        return discountPurchasesAmount;
    }

    public void setDiscountPurchasesAmount(String discountPurchasesAmount) {
        this.discountPurchasesAmount = discountPurchasesAmount;
    }

    public String getAcceptedCampaign1() {
        return acceptedCampaign1;
    }

    public void setAcceptedCampaign1(String acceptedCampaign1) {
        this.acceptedCampaign1 = acceptedCampaign1;
    }

    public String getAcceptedCampaign2() {
        return acceptedCampaign2;
    }

    public void setAcceptedCampaign2(String acceptedCampaign2) {
        this.acceptedCampaign2 = acceptedCampaign2;
    }

    public String getAcceptedCampaign3() {
        return acceptedCampaign3;
    }

    public void setAcceptedCampaign3(String acceptedCampaign3) {
        this.acceptedCampaign3 = acceptedCampaign3;
    }

    public String getAcceptedCampaign4() {
        return acceptedCampaign4;
    }

    public void setAcceptedCampaign4(String acceptedCampaign4) {
        this.acceptedCampaign4 = acceptedCampaign4;
    }

    public String getAcceptedCampaign5() {
        return acceptedCampaign5;
    }

    public void setAcceptedCampaign5(String acceptedCampaign5) {
        this.acceptedCampaign5 = acceptedCampaign5;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getWebPurchasesAmount() {
        return webPurchasesAmount;
    }

    public void setWebPurchasesAmount(String webPurchasesAmount) {
        this.webPurchasesAmount = webPurchasesAmount;
    }

    public String getCatalogPurchasesAmount() {
        return catalogPurchasesAmount;
    }

    public void setCatalogPurchasesAmount(String catalogPurchasesAmount) {
        this.catalogPurchasesAmount = catalogPurchasesAmount;
    }

    public String getStorePurchasesAmount() {
        return storePurchasesAmount;
    }

    public void setStorePurchasesAmount(String storePurchasesAmount) {
        this.storePurchasesAmount = storePurchasesAmount;
    }

    public String getVisitsAmount() {
        return visitsAmount;
    }

    public void setVisitsAmount(String visitsAmount) {
        this.visitsAmount = visitsAmount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", education='" + education + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", income='" + income + '\'' +
                ", kidsAmount='" + kidsAmount + '\'' +
                ", teensAmount='" + teensAmount + '\'' +
                ", enrollmentDate='" + enrollmentDate + '\'' +
                ", recency='" + recency + '\'' +
                ", complains='" + complains + '\'' +
                ", wineAmount='" + wineAmount + '\'' +
                ", fruitsAmount='" + fruitsAmount + '\'' +
                ", meatAmount='" + meatAmount + '\'' +
                ", fishAmount='" + fishAmount + '\'' +
                ", sweetAmount='" + sweetAmount + '\'' +
                ", goldAmount='" + goldAmount + '\'' +
                ", discountPurchasesAmount='" + discountPurchasesAmount + '\'' +
                ", acceptedCampaign1='" + acceptedCampaign1 + '\'' +
                ", acceptedCampaign2='" + acceptedCampaign2 + '\'' +
                ", acceptedCampaign3='" + acceptedCampaign3 + '\'' +
                ", acceptedCampaign4='" + acceptedCampaign4 + '\'' +
                ", acceptedCampaign5='" + acceptedCampaign5 + '\'' +
                ", response='" + response + '\'' +
                ", webPurchasesAmount='" + webPurchasesAmount + '\'' +
                ", catalogPurchasesAmount='" + catalogPurchasesAmount + '\'' +
                ", storePurchasesAmount='" + storePurchasesAmount + '\'' +
                ", visitsAmount='" + visitsAmount + '\'' +
                '}';
    }

}
