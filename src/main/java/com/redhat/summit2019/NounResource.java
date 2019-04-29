package com.redhat.summit2019;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.redhat.summit2019.model.Noun;

@Path("/noun")
@ApplicationScoped
public class NounResource {

    private List<Noun> nouns = new ArrayList<>();

    @GET
    @Path("/")
    @Produces("application/json")
    public Noun greeting() {
        return nouns.get(new Random().nextInt(nouns.size()));
    }

    @PostConstruct
    public void loadData() {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("nouns.txt");
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                reader.lines()
                        .forEach(adj -> nouns.add(new Noun(adj.trim())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}