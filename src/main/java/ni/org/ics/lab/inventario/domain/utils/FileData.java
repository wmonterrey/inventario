package ni.org.ics.lab.inventario.domain.utils;

/**
 * @author ics
 */
public class FileData {

    private String aliCode;
    private String equipCode;
    private String rackCode;
    private String boxCode;
    private int aliPosition;
    private String aliRes;
    private float aliVol;
    private String recordDate;
    private String recordUser;

    public String getAliCode() {
        return aliCode;
    }

    public void setAliCode(String aliCode) {
        this.aliCode = aliCode;
    }

    public String getEquipCode() {
        return equipCode;
    }

    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }

    public String getRackCode() {
        return rackCode;
    }

    public void setRackCode(String rackCode) {
        this.rackCode = rackCode;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public int getAliPosition() {
        return aliPosition;
    }

    public void setAliPosition(int aliPosition) {
        this.aliPosition = aliPosition;
    }

    public String getAliRes() {
        return aliRes;
    }

    public void setAliRes(String aliRes) {
        this.aliRes = aliRes;
    }

    public float getAliVol() {
        return aliVol;
    }

    public void setAliVol(float aliVol) {
        this.aliVol = aliVol;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }
}
