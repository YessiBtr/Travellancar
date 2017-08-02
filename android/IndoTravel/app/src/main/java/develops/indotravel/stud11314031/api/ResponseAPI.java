package develops.indotravel.stud11314031.api;

import java.util.List;

import develops.indotravel.stud11314031.model.News;

/**
 * Created by Vranata on 14/05/2017.
 */

public class ResponseAPI {
    private String error;
    private List<News> hasil;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<News> getHasil() {
        return hasil;
    }
    public void setHasil(List<News> hasil) {
        this.hasil = hasil;
    }

}
