package net.k40s;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import de.micromata.azubi.Textie;
import de.micromata.azubi.model.Dungeon;



@Path("textie")
public class Receiver {

    @POST
    @Path("command")
    @Consumes("application/json")
    @Produces("text/plain")
    public String handleInput(TextieCommand input, @Context HttpServletRequest req) {

        Dungeon dungeon;

        String token = input.getToken();
        String command = input.getCommand();
        if(token.equals(req.getSession().getAttribute("token"))) {

            if(req.getSession().getAttribute("dungeon") == null) {
                req.getSession().setAttribute("dungeon", Dungeon.createDungeon(DBUtils.getStandardConfig()));
                dungeon = (Dungeon) req.getSession().getAttribute("dungeon");
                dungeon.initDoorSchalter();
                dungeon.getPlayer().setPosition(dungeon.findRoomByNumber(1));
            } else {
                dungeon = (Dungeon) req.getSession().getAttribute("dungeon");
            }

            Textie.diag = false;
            Textie.webapp = true;
            Textie.lastPrintedText = "";
            String[] parsedargs = {""};
            String[] parsedcmd = command.split(" ", 2);
            if(parsedcmd.length > 1) {
                parsedargs = parsedcmd[1].split(" ", 2);
            }

            Textie.executeCommand(parsedcmd,
                parsedargs, dungeon);
            Thread.yield();

            req.getSession().setAttribute("dungeon", dungeon);

            return Textie.lastPrintedText;

        } else {
            return "ERROR! PLEASE LOG IN AGAIN!";
        }
    }
    
    @POST
    @Path("saveconfig")
    @Consumes("application/json")
    @Produces("text/plain")
    public String saveConfig(TextieConfig input, @Context HttpServletRequest req){
        String token = input.getToken();
        String config = input.getConfig();
        String user;
        if(token.equals(req.getSession().getAttribute("token"))) {
            user = (String) req.getSession().getAttribute("user");
            DBUtils.updateConfig(user, config);
            return "true";
        } else {
            return "false";
        }
        
    }

    @GET
    @Path("getconfig")
    @Produces("text/plain")
    public String saveConfig(@Context HttpServletRequest req){
        String user;
        user = (String) req.getSession().getAttribute("user");
        return DBUtils.getConfig(user);
    }
}
