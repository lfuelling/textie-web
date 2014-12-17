package net.k40s;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;



@Path("textie")
public class Receiver {
    
    private Dungeon dungeon = Dungeon.createDungeon(DBUtils.getStandardConfig());


    @POST
    @Path("command")
    @Consumes("application/json")
    @Produces("text/plain")
    public String handleInput(TextieInput input) {

        String token = input.getToken();
        String command = input.getCommand();

        Textie.diag = false;
        Textie.webapp = true;
        Textie.lastPrintedText = "";
        String[] parsedargs = {""};
        String[] parsedcmd = command.split(" ", 2);
        if (parsedcmd.length > 1) {
            parsedargs = parsedcmd[1].split(" ", 2);
        }

        Textie.executeCommand(parsedcmd,
                parsedargs, dungeon);
        Thread.yield();

        return Textie.lastPrintedText;

    }
}
