package net.k40s;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Hier sind alle Datenbankfunktionen drin.
 *
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class DBUtils {

  public static DBCollection getCollection(String collectionName) {
    MongoClient mongoClient = null;
    try {
      mongoClient = new MongoClient();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    DB db = mongoClient.getDB("textieWeb");

    DBCollection coll = db.getCollection(collectionName);

    return coll;
  }

  public static Object getStandardConfig(String username) {
    DBObject query = new BasicDBObject("user", username);
    DBObject data = getCollection("userConfigs").findOne(query);

    if (data == null) {
      return null;
    } else {
    }
    return data.get("config");

  }

  public static Object getConfig(String username, int slot) {
    DBObject query = new BasicDBObject("user", username);
    query.put("slot", String.valueOf(slot));
    DBObject data = getCollection("altConfigs").findOne(query);

    if (data == null) {
      return null;
    } else {
    }
    return data.get("config");

  }

  public static void updateConfig(String username, int slot, String config){
    DBObject updateData = new BasicDBObject("user", username);
    updateData.put("slot", String.valueOf(slot));
    updateData.put("config", config);
    updateData.put("savegame", "");
    DBObject query = new BasicDBObject("user", username);
    query.put("slot", slot);
    getCollection("altConfigs").update(query, updateData, true, false);

  }

  public static void updateSavegame(String username, int slot, String savegame){
    DBObject updateData = new BasicDBObject("user", username);
    updateData.put("slot", String.valueOf(slot));
    updateData.put("config", getConfig(username, slot));
    updateData.put("savegame", savegame);
    DBObject query = new BasicDBObject("user", username);
    query.put("slot", slot);
    getCollection("altConfig").update(query, updateData, true, false);
  }


  public static String getPassword(String username){

    DBObject result = getCollection("userConfigs").findOne(username);
    return (String) result.get("password");
  }

  public static String createUser(String name, String password) {

    final String standardConfig = "[\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.Room\",\n" +
            "    \"roomNumber\": 1,\n" +
            "    \"welcomeText\": \"Du befindest dich in einem dunklen Raum. Nach einiger Zeit gewöhnen sich deine Augen an die Dunkelheit.\",\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.ToggleItem\",\n" +
            "          \"state\": false,\n" +
            "          \"name\": \"Fackel\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Du betrachtest die Fackel. Wie kann man die wohl anzünden?\",\n" +
            "          \"useText\": \"Du zündest deine Fackel mit dem Feuerzeug an.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Handtuch\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Das Handtuch sieht sehr flauschig aus.\",\n" +
            "          \"useText\": \"Du wischst dir den Angstschweiß von der Stirn.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.StorageItem\",\n" +
            "          \"name\": \"Truhe\",\n" +
            "          \"inventory\": {\n" +
            "            \"items\": []\n" +
            "          },\n" +
            "          \"lockState\": true,\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Die Truhe ist verschlossen. Es sieht nicht so aus, als könnte man sie aufbrechen.\",\n" +
            "          \"useText\": \"Du kannst die Truhe nicht öffnen.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Switch\",\n" +
            "          \"name\": \"Schalter\",\n" +
            "          \"state\": false,\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Da ist ein kleiner Schalter an der Wand.\",\n" +
            "          \"useText\": \"Du hörst ein Rumpeln, als du den Schalter drückst.\",\n" +
            "          \"doorIds\": [2, 7]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 1,\n" +
            "        \"nextRoom\": 2,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"SUED\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 2,\n" +
            "        \"nextRoom\": 4,\n" +
            "        \"locked\": true,\n" +
            "        \"direction\": \"WEST\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.Room\",\n" +
            "    \"roomNumber\": 2,\n" +
            "    \"welcomeText\": \"Du kommst in einen dunklen Raum.\",\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Stein\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Du betrachtest den Stein. Er wirkt kalt.\",\n" +
            "          \"useText\": \"Du wirfst den Stein vor dir auf den Boden und hebst ihn wieder auf. Was ein Spaß.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Schwert\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Du betrachtest das Schwert. Es sieht sehr scharf aus.\",\n" +
            "          \"useText\": \"Du stichst dir das Schwert zwischen die Rippen und stirbst.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Feuerzeug\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Du betrachtest das Feuerzeug. Es wirkt zuverlässig.\",\n" +
            "          \"useText\": \"Du zündest deine Fackel mit dem Feuerzeug an.\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 3,\n" +
            "        \"nextRoom\": 3,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"WEST\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 4,\n" +
            "        \"nextRoom\": 1,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"NORD\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.DarkRoom\",\n" +
            "    \"roomNumber\": 3,\n" +
            "    \"welcomeText\": \"Es ist zu dunkel, um etwas zu sehen. Ein seltsamer Geruch liegt in der Luft.\",\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Falltür\",\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Da ist eine Falltür\",\n" +
            "          \"useText\": \"Du schlüpfst durch die Falltür in den darunterliegenden Raum.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Whiteboard\",\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Es steht \\\"FLIEH!\\\" mit Blut geschrieben darauf.\",\n" +
            "          \"useText\": \"Das fasse ich bestimmt nicht an!\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Brecheisen\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Da ist ein Brecheisen, es ist \\\"Gordon\\\" eingeritzt.\",\n" +
            "          \"useText\": \"Du kratzt dich mit dem Brecheisen am Kopf\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Quietscheente\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Die Ente schaut dich vorwurfsvoll an.\",\n" +
            "          \"useText\": \"Die Ente schaut dich vorwurfsvoll an und quietscht leise, als du sie zusammendrückst.\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 5,\n" +
            "        \"nextRoom\": 4,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"FALLTUER\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 6,\n" +
            "        \"nextRoom\": 2,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"OST\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.Room\",\n" +
            "    \"roomNumber\": 4,\n" +
            "    \"welcomeText\": \"Du kommst in einen hell erleuchteten Raum. Ein alter Mann lehnt an der Wand.\",\n" +
            "    \"human\": {\n" +
            "      \"name\": \"Gordon\",\n" +
            "      \"dialog1\": \"Hast du die Truhe gesehen? Ich frage mich, was da wohl drin ist...\",\n" +
            "      \"dialog2\": \"...\",\n" +
            "      \"questDoneText\": \"Sehr gut. Danke dir.\",\n" +
            "      \"questText\": \"Ich suche ein Brecheisen. Hast du eins?\",\n" +
            "      \"questItem\": \"Brecheisen\",\n" +
            "      \"rewardItem\": {\n" +
            "        \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "        \"name\": \"Schlüssel\",\n" +
            "        \"pickable\": true,\n" +
            "        \"examineText\": \"Du betrachtest den Schlüssel. Was kann man damit wohl aufschließen?\",\n" +
            "        \"useText\": \"Hier gibt es nichts um den Schlüssel zu benutzen.\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Sack\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Du betrachtest den Sack. Vielleicht kannst du ihn ja an deinem Rucksack befestigen.\",\n" +
            "          \"useText\": \"Du bindest den Sack an deinen Rucksack.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Switch\",\n" +
            "          \"name\": \"Schalter\",\n" +
            "          \"state\": false,\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Da ist ein kleiner Schalter an der Wand.\",\n" +
            "          \"useText\": \"Du hörst ein Rumpeln, als du den Schalter drückst.\",\n" +
            "          \"doorIds\": [7, 2]\n" +
            "        },\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Karte\",\n" +
            "          \"name\": \"Karte\",\n" +
            "          \"pickable\": true,\n" +
            "          \"examineText\": \"Das ist eine Karte, sie zeigt deinen Laufweg.\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 7,\n" +
            "        \"nextRoom\": 1,\n" +
            "        \"locked\": true,\n" +
            "        \"direction\": \"OST\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 8,\n" +
            "        \"nextRoom\": 5,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"WEST\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 9,\n" +
            "        \"nextRoom\": 7,\n" +
            "        \"locked\": true,\n" +
            "        \"direction\": \"NORD\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.Room\",\n" +
            "    \"roomNumber\": 5,\n" +
            "    \"welcomeText\": \"Du kommst in einen Raum, in dem eine Junge steht.\",\n" +
            "    \"human\": {\n" +
            "      \"name\": \"Junge\",\n" +
            "      \"dialog1\": \"Ich suche meine Mutter.\",\n" +
            "      \"dialog2\": \"Finde sie!\",\n" +
            "      \"questDoneText\": \"Danke\",\n" +
            "      \"questText\": \"Hier ein Brief bring ihn zu einer Frau.\",\n" +
            "      \"questItem\": \"Handtuch\",\n" +
            "      \"rewardItem\": {\n" +
            "        \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "        \"name\": \"Brief\",\n" +
            "        \"pickable\": true,\n" +
            "        \"useText\": \"Bringe den Brief zu einer Frau\",\n" +
            "        \"examineText\": \"Ein Brief adressiert an eine Frau.\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "          \"name\": \"Falltür\",\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Da ist eine Falltür\",\n" +
            "          \"useText\": \"Du schlüpfst durch die Falltür in den darunterliegenden Raum.\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 10,\n" +
            "        \"nextRoom\": 6,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"FALLTUER\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 11,\n" +
            "        \"nextRoom\": 4,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"OST\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.Room\",\n" +
            "    \"roomNumber\": 6,\n" +
            "    \"welcomeText\": \"Du kommst in einen Raum mit einer Truhe.\",\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.StorageItem\",\n" +
            "          \"lockState\": false,\n" +
            "          \"inventory\": {\n" +
            "            \"items\": [\n" +
            "              {\n" +
            "                \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "                \"name\": \"Axt\",\n" +
            "                \"pickable\": true,\n" +
            "                \"useText\": \"Du schlägst mit der Axt zu.\",\n" +
            "                \"examineText\": \"Eine scharfe Axt.\"\n" +
            "              }\n" +
            "            ]\n" +
            "          },\n" +
            "          \"class\": \"de.micromata.azubi.model.StorageItem\",\n" +
            "          \"name\": \"Truhe\",\n" +
            "          \"pickable\": false,\n" +
            "          \"useText\": \"Du versuchst die Truhe zu öffnen.\",\n" +
            "          \"examineText\": \"Ein große Truhe aus Holz.\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 12,\n" +
            "        \"nextRoom\": 7,\n" +
            "        \"locked\": true,\n" +
            "        \"direction\": \"OST\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"class\": \"de.micromata.azubi.model.Room\",\n" +
            "    \"roomNumber\": 7,\n" +
            "    \"welcomeText\": \"Du kommst in einen Raum, eine Frau steht mitten im Raum.\",\n" +
            "    \"human\": {\n" +
            "      \"name\": \"Frau\",\n" +
            "      \"dialog1\": \"Du hast mein Sohn gesehen ?\",\n" +
            "      \"dialog2\": \"Wo ?\",\n" +
            "      \"questDoneText\": \"Danke, Hier ein Seil für dich.\",\n" +
            "      \"questItem\": \"Brief\",\n" +
            "      \"rewardItem\": {\n" +
            "        \"class\": \"de.micromata.azubi.model.Item\",\n" +
            "        \"name\": \"Seil\",\n" +
            "        \"pickable\": true,\n" +
            "        \"examineText\": \"Ein stabiles Seil.\",\n" +
            "        \"useText\": \"Du bindest das Seil fest.\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"inventory\": {\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"class\": \"de.micromata.azubi.model.Switch\",\n" +
            "          \"name\": \"Schalter\",\n" +
            "          \"state\": false,\n" +
            "          \"useText\": \"Du hörst ein Rumpeln, als du den Schalter drückst.\",\n" +
            "          \"pickable\": false,\n" +
            "          \"examineText\": \"Da ist ein kleiner Schalter an der Wand.\",\n" +
            "          \"doorIds\": [13]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"doors\": [\n" +
            "      {\n" +
            "        \"doorId\": 13,\n" +
            "        \"nextRoom\": 4,\n" +
            "        \"locked\": true,\n" +
            "        \"direction\": \"SUED\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"doorId\": 14,\n" +
            "        \"nextRoom\": 6,\n" +
            "        \"locked\": false,\n" +
            "        \"direction\": \"WEST\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";


    DBObject query = new BasicDBObject("name", name);
    DBObject data = getCollection("userConfigs").findOne(query);

    if (data != null) {
      return "Es existiert schon ein Benutzer mit dem Namen \"" + name + "\".";
    } else {
      BasicDBObject doc = new BasicDBObject("user", name)
              .append("password", password)
              .append("config", standardConfig);
      getCollection("userConfigs").insert(doc);
      return "Benutzer \"" + name + "\" wurde angelegt.";
    }
  }




}
