package net.k40s;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;



@Path("textie")
public class Receiver {

    @POST
    @Path("command")
    @Consumes("application/json")
    @Produces("text/plain")
    public String handleInput(TextieInput input, @Context HttpServletRequest req) {
        Dungeon dungeon;

        String token = input.getToken();
        String command = input.getCommand();

        if(req.getSession().getAttribute("dungeon") == null){
            req.getSession().setAttribute("dungeon", Dungeon.createDungeon(DBUtils.getStandardConfig()));
            dungeon = (Dungeon) req.getSession().getAttribute("dungeon");
            dungeon.getPlayer().setPosition(dungeon.findRoomByNumber(1));
        } else {
            dungeon = (Dungeon) req.getSession().getAttribute("dungeon");
        }

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

        req.getSession().setAttribute("dungeon", dungeon);

        return Textie.lastPrintedText;

    }
}
