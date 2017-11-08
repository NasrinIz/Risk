package src.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class is used to instantiate the map editor, to enable the user
 * to add or delete the country, continent.
 *
 * @author Team20
 */
public class MapEditor {
    final int CHOICENONE = 0;
    final int CHOICEADDCONT = 1;
    final int CHOICEDELCONT = 2;
    final int CHOICEADDCOUNT = 3;
    final int CHOICEDELCOUNT = 4;

    private GenericFunctions genericFunctionsObj = new GenericFunctions();
    private Integer editorMode = genericFunctionsObj.EDITORMODENONE;
    /**
     * The map object containing, the map information being edited or created.
     */
    public Maps mapObj = null;
    private List<Territory> addedTerritories = new ArrayList<Territory>();
    private List<Continent> addedContinents = new ArrayList<Continent>();
    private Integer choice = 0;
    private String mapName = null;

    /**
     * This is the constructor to the class MapEditor and starts the flow
     * of editor towards editing the previously existing map or creating a new map
     * on the basis of received parameters.
     *
     * @param inMapName    map location
     * @param inEditorMode determines whether the flow edits previous map or creates new.
     */
    public MapEditor(Integer inEditorMode, String inMapName) {
        mapName = inMapName;
        editorMode = inEditorMode;

        if (editorMode == genericFunctionsObj.EDITORMODEEDIT) {
            editMap();
        } else if (editorMode == genericFunctionsObj.EDITORMODECREATE) {
            createMap();
        }

    }

    /**
     * This function is used to edit the map.
     */
    private void editMap() {
        //mapName = mapName.substring(0, mapName.length() - 4);
        mapObj = new Maps(mapName, 1);
        if (!mapObj.readMap().equals("true")) {
            System.out.println("Input Map Not correct. Opening to edit for correct");
        }
        System.out.println("Map Opened");
    }

    /**
     * This function is sued to create a new map.
     */
    private void createMap() {
        mapObj = new Maps(mapName, 1);
    }

    /**
     * This function displays the information existing in map object.
     */
    private void displayMap() {
        Iterator ite = mapObj.getDictContinents().entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            Continent tmpContinent = mapObj.getDictContinents().get(pair.getKey());
            System.out.printf("\n%s, %d", tmpContinent.getName(), tmpContinent.getArmyReward());
        }
        System.out.println();
        ite = mapObj.getDictTerritory().entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            Territory tmpTerritory = mapObj.getDictTerritory().get(pair.getKey());
            System.out.printf("\n%s, %d, %d, %s", tmpTerritory.getName(), tmpTerritory.getX(),
                    tmpTerritory.getY(), tmpTerritory.getContinent());
            for (int i = 0; i < tmpTerritory.getAdjacentCountries().size(); i++) {
                System.out.printf(", %s,", tmpTerritory.getAdjacentCountries().get(i));
            }
        }
    }

    /**
     * This function is used to add continent to the map object
     *
     * @param continentInfo The information about the new continent to be added.
     */
    public void addContinent(String continentInfo) {
        List<String> continentInfoList = genericFunctionsObj.genCommaSepStrToArrayList(continentInfo);
        String continentName = continentInfoList.get(0);
        Integer continentAward = Integer.parseInt(continentInfoList.get(1));
        if (editorMode == genericFunctionsObj.EDITORMODEEDIT) {
            Iterator ite = mapObj.getDictContinents().entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry pair = (Map.Entry) ite.next();
                if (pair.getKey().equals(continentName)) {
                    System.out.println("A continent with this name already exists. Updating the army reward values.");
                    mapObj.getDictContinents().get(pair.getKey()).setArmyReward(continentAward);
                }
            }
        }

        Continent tmpCont = new Continent(continentName, continentAward);
        mapObj.getDictContinents().put(continentName, tmpCont);
        displayMap();
    }

    /**
     * This function is used to delete the continent.
     *
     * @param continentInfo Information for the continent to be deleted.
     */
    public void deleteContinent(String continentInfo) {
        List<String> continentInfoList = genericFunctionsObj.genCommaSepStrToArrayList(continentInfo);
        String continentName = continentInfo;

        if (mapObj.getDictContinents().containsKey(continentName)) {
            mapObj.deleteContinent(continentName);
        } else {
            System.out.println("No such continent exists");
        }
        displayMap();
    }


    /**
     * This function is sued to create a new country
     *
     * @param countryInfo The information of new country to be created
     */
    public void newCountry(String countryInfo) {
        Territory tmpTerritory = new Territory(countryInfo);

        if (tmpTerritory != null) {
            if (mapObj.getDictContinents().containsKey(tmpTerritory.getContinent())) {
                mapObj.getDictTerritory().put(tmpTerritory.getName(), tmpTerritory);
                List<Territory> continentTerritories = mapObj.getDictContinents().get(tmpTerritory.getContinent()).getTerritories();
                continentTerritories.add(tmpTerritory);
            } else {
                System.out.printf("\nNo continent found for territory with name %s. "
                        + "Please add this continent first\n", tmpTerritory.getContinent());
            }
        }
        displayMap();
    }

    /**
     * This function is sued to delete the country.
     *
     * @param countryInfo The information for the country to be deleted.
     */
    public void delCountry(String countryInfo) {
        if (mapObj.getDictTerritory().containsKey(countryInfo)) {
            mapObj.deleteTerritory(mapObj.getDictTerritory().get(countryInfo));
        } else {
            System.out.println("No such territory exists");
        }
        displayMap();
    }

    /**
     * This function is used to retrieve the list of continents inside the map object
     *
     * @return mapTitles The list of continents inside the map object.
     */
    public String[] getContinentListInMapEditor() {
        String[] mapTitles = new String[mapObj.getDictContinents().size()];
        Iterator ite = mapObj.getDictContinents().entrySet().iterator();
        int i = 0;
        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            mapTitles[i] = (String) pair.getKey();
            i++;
        }
        return mapTitles;
    }


    /**
     * This function is used to retrieve the list of countries inside the map object.
     *
     * @return mapTitles The list of countries inside the map object.
     */
    public String[] getCountryListInMapEditor() {
        String[] mapTitles = new String[mapObj.getDictTerritory().size()];
        Iterator ite = mapObj.getDictTerritory().entrySet().iterator();
        int i = 0;
        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            mapTitles[i] = (String) pair.getKey();
            i++;
        }
        return mapTitles;
    }

    /**
     * This function is used to finish map editing and call for validation.
     *
     * @param inPath The path where the edited map is to be saved.
     * @return Indicates whether the validation succeeded or failed.
     */
    public int finishAndValidate(String inPath) {
        Integer rt = 0;

        if (mapObj.validateMap() != "true") {
            System.out.println("The edited map is not valid");
            rt = -1;
        }

        if (rt == 0) {
            if (this.editorMode == genericFunctionsObj.EDITORMODEEDIT) {
                String path = String.format("Resources//Maps//%s.map", mapName);
                mapObj.writeMapToFile(path);
            } else if (this.editorMode == genericFunctionsObj.EDITORMODECREATE) {
                mapObj.writeMapToFile(inPath);
            }
        }
        return rt;
    }

}

