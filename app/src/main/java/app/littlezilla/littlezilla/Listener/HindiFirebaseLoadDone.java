package app.littlezilla.littlezilla.Listener;

import java.util.List;

import app.littlezilla.littlezilla.Models.EnglishModel;
import app.littlezilla.littlezilla.Models.HindiModel;

public interface HindiFirebaseLoadDone {

    void onFirebaseLoadSuccess(List<HindiModel> modelList);
    void onFirebaseLoadFailed(String message);
}
