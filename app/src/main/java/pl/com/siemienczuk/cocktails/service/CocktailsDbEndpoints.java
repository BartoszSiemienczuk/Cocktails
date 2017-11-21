package pl.com.siemienczuk.cocktails.service;

/**
 * Created by b.siemienczuk on 2017-11-21.
 */

public abstract class CocktailsDbEndpoints {
    public static final String BASE_URL = "http://www.thecocktaildb.com/api/json/v1/";
    public static final String API_KEY = "1/";
    public static final String CATEGORY_LIST = BASE_URL + API_KEY + "list.php?c=list";
    public static final String GLASSES_LIST = BASE_URL + API_KEY + "list.php?c=list";
    public static final String INGREDIENTS_LIST = BASE_URL + API_KEY + "list.php?c=list";
    public static final String ALCOHOLIC_LIST = BASE_URL + API_KEY + "list.php?c=list";
    public static final String FILTER_BY_CATEGORY = BASE_URL + API_KEY + "filter.php?c=Cocktail";

}
