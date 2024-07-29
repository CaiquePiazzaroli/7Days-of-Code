package com.imdb.main;
import com.imdb.models.*;
import com.imdb.superclass.Titulo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        //Chamando a api tmdb
        //Token retirado do site
        String token = "Token";

        //Gettin Tv Shows
        String urlSeries = "https://api.themoviedb.org/3/tv/popular?language=en-US&page=1";
        TmdbApiClient imdbTv = new TmdbApiClient(token,urlSeries);
        ParserTvTmdb parseadorTv = new ParserTvTmdb(imdbTv.getJson());
        ArrayList<Titulo> tvList = parseadorTv.getArrayTitles();

        //Getting movies
        String urlFilmes = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";
        TmdbApiClient imdbMovies = new TmdbApiClient(token,urlFilmes);
        ParserMovieTmdb parserMovie = new ParserMovieTmdb(imdbMovies.getJson());
        ArrayList<Titulo> movieList = parserMovie.getArrayTitles();

        //Criando o html e escrevendo no html
        HTMLGenerator html = new HTMLGenerator("index.html");
        Collections.sort(movieList);
        Collections.sort(tvList);
        //html.writeBody(movieList,"des");


        //Conecting to marvel API
        String publicApiKey = "publicKey";
        String privateApiKey = "PrivateKey";
        String url = "https://gateway.marvel.com:443/v1/public/comics?orderBy=focDate&limit=20";
        String timeStamp = "1";

        MarvelApiClient marvelImdb = new MarvelApiClient(publicApiKey,privateApiKey,timeStamp, url);
        ParserComicsMarvel parserComics = new ParserComicsMarvel(marvelImdb.getJson());
        ArrayList<Titulo> comicsList = parserComics.getArrayTitles();
        Collections.sort(comicsList);
        html.writeBody(comicsList,"des");



    }
}
