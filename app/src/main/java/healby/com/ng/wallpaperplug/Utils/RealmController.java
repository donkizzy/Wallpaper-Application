package healby.com.ng.wallpaperplug.Utils;

import java.util.List;

import healby.com.ng.wallpaperplug.Models.Photos;
import io.realm.Realm;

public class RealmController {
    private  final Realm realm ;
    public RealmController (){
        realm = Realm.getDefaultInstance();
    }

    public void savePhoto(Photos photos){
        realm.beginTransaction();
        realm.copyToRealm(photos);
        realm.commitTransaction();
    }

    public void deletePhoto(final Photos photos){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Photos photos1 = realm.where(Photos.class).equalTo("id",photos.getId()).findFirst();
                photos1.deleteFromRealm();
            }
        });

    }

    public boolean isExistPhoto(String  photoId){
        Photos photos1 = realm.where(Photos.class).equalTo("id",photoId).findFirst();
        if (photos1 == null)
            return  false ;
        return  true ;
    }

    public List<Photos> getPhotos(){

        return realm.where(Photos.class).findAll() ;

    }
}
