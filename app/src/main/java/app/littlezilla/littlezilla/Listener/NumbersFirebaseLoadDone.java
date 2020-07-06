package app.littlezilla.littlezilla.Listener;

import java.util.List;

import app.littlezilla.littlezilla.Models.HindiModel;
import app.littlezilla.littlezilla.Models.NumbersModel;

public interface NumbersFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<NumbersModel> modelList);
    void onFirebaseLoadFailed(String message);
}
