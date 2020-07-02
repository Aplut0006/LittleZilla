package app.littlezilla.littlezilla.Listener;

import java.util.List;

import app.littlezilla.littlezilla.Models.EnglishModel;

public interface FirebaseLoadDone {

    void onFirebaseLoadSuccess(List<EnglishModel> modelList);
    void onFirebaseLoadFailed(String message);
}
