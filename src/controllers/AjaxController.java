package controllers;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.SongModel;
import lib.repositories.FileRepository;
import org.json.*;

public class AjaxController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileRepository repository;
	private static final int ONPAGE = 5;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		try{
			try{
				repository = new FileRepository("/home/alsp/repository/db");

				if(req.getParameter("do").equals("get")){	
					resp.getWriter().write((new JSONObject()).put("data",
							repository.get(ONPAGE,
									Integer.parseInt(req.getParameter("offset")))).toString());

				} else if(req.getParameter("do").equals("getByKey"))
					resp.getWriter().write(repository.get(req.getParameter("key")).toJson().toString());
				else if(req.getParameter("do").equals("add")){
					Random rand = new Random();
					repository.add(new SongModel("Nothing else matters" + rand.nextInt(),
							"Metallica", "Metallica", 1992, 6.28f));
					repository.add(new SongModel("Unforgiven" + rand.nextInt(),
							"Metallica", "Metallica", 1991, 6.27f));
					repository.add(new SongModel("Fade to black" + rand.nextInt(),
							"Metallica", "Ride the Lightning", 1984, 6.57f));
					
					resp.getWriter().write(
							(new JSONObject()).put("message", "Action complete").toString());

				} else if(req.getParameter("do").equals("set")){
					repository.set(
							new SongModel(
									new JSONObject(req.getParameter("object")
											)));
					
					resp.getWriter().write(
							(new JSONObject()).put("message", "Action complete").toString());

				} else if(req.getParameter("do").equals("remove")){
					JSONArray keys = new JSONArray(req.getParameter("keys"));
					
					for (int i = 0; i < keys.length(); i++) {
						repository.remove(keys.getString(i));
					}
					
					resp.getWriter().write(
							(new JSONObject()).put("message", "Action complete").toString());
					
				} else 
					throw new IllegalArgumentException("Illegal action");
				
				repository.close();
				
			} catch (NullPointerException npe){
				throw new IllegalArgumentException("Required argument not specified");
			} catch (JSONException e) {
				throw new IllegalArgumentException("Invalid json object (" + e.getMessage() + ")");
			}
			
		} catch(Exception e){
			try {
				resp.getWriter().write((new JSONObject()).put("error", e.getMessage()).toString());
			} catch (JSONException e1) {
				resp.getWriter().write("Kernel panic!!!");
			}
		}
		
		
	}
}
