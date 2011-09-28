package org.tangram.gae.solution;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Article extends Linkable {

    @Persistent
    private Text text;


    public Text getText() {
        return text;
    }


    public void setText(Text text) {
        this.text = text;
    }

} // Article
