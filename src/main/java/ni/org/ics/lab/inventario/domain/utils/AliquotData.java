package ni.org.ics.lab.inventario.domain.utils;

import org.joda.time.DateTime;

/**
 * @author ics
 */
public class AliquotData {

    private String codAlic;
    private int posBox;
    private int codFreezer;
    private String codRack;
    private int codBox;
    private String posNeg;
    private String regDate;
    private String codUser;
    private String aliVol;
    private float weight;
    private String type;
    private String condition;
    private String separated;
    private int numDesc;
    private String destination;
    private String user;
    private String outputDate;
    private String usedVol;
    private String use;
    private String useDate;
    private String userUse;
    private String table;

    public String getCodAlic() {
        return codAlic;
    }

    public void setCodAlic(String codAlic) {
        this.codAlic = codAlic;
    }

    public int getPosBox() {
        return posBox;
    }

    public void setPosBox(int posBox) {
        this.posBox = posBox;
    }

    public String getCodRack() {
        return codRack;
    }

    public void setCodRack(String codRack) {
        this.codRack = codRack;
    }

    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    public int getNumDesc() {
        return numDesc;
    }

    public void setNumDesc(int numDesc) {
        this.numDesc = numDesc;
    }

    public int getCodFreezer() {
        return codFreezer;
    }

    public void setCodFreezer(int codFreezer) {
        this.codFreezer = codFreezer;
    }

    public int getCodBox() {
        return codBox;
    }

    public void setCodBox(int codBox) {
        this.codBox = codBox;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSeparated() {
        return separated;
    }

    public void setSeparated(String separated) {
        this.separated = separated;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(String outputDate) {
        this.outputDate = outputDate;
    }

    public String getUsedVol() {
        return usedVol;
    }

    public void setUsedVol(String usedVol) {
        this.usedVol = usedVol;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPosNeg() {
        return posNeg;
    }

    public void setPosNeg(String posNeg) {
        this.posNeg = posNeg;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getAliVol() {
        return aliVol;
    }

    public void setAliVol(String aliVol) {
        this.aliVol = aliVol;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getUserUse() {
        return userUse;
    }

    public void setUserUse(String userUse) {
        this.userUse = userUse;
    }
}
