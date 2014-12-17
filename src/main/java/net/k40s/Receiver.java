package net.k40s;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;



@Path("textie")
public class Receiver {
    public Dungeon dungeon = Dungeon.createDungeon(DBUtils.getStandardConfig());


    @POST
    @Path("command")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String handleInput(String input) {
        Textie.diag = false;
        Textie.webapp = true;
        Textie.lastPrintedText = "";
        String[] parsedargs = {""};
        String[] parsedcmd = input.split(" ", 2);
        if (parsedcmd.length > 1) {
            parsedargs = parsedcmd[1].split(" ", 2);
        }
        Textie.executeCommand(parsedcmd,
                parsedargs, dungeon);
        Thread.yield();

        return Textie.lastPrintedText;

    }
}
