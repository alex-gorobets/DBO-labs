package controllers;

import java.io.IOException;

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

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		try{
			try{
				repository = new FileRepository("/home/alsp/repository/db");

				if(req.getParameter("do").equals("getAll"))
					resp.getWriter().write((new JSONObject()).put("data", repository.get()).toString());
				else if(req.getParameter("do").equals("getByKey"))
					resp.getWriter().write(repository.get(req.getParameter("key")).toJson().toString());
				else if(req.getParameter("do").equals("add")){
					repository.add(new SongModel("Nothing else matters",
							"Metallica", "Metallica", 1992, 6.28f));
					repository.add(new SongModel("Unforgiven",
							"Metallica", "Metallica", 1991, 6.27f));
					repository.add(new SongModel("Fade to black",
							"Metallica", "Ride the Lightning", 1984, 6.57f));
					
					resp.getWriter().write("Added");
				} else if(req.getParameter("do").equals("set")){
					repository.set(
							new SongModel(
									new JSONObject(req.getParameter("object")
											)));
				} else if(req.getParameter("do").equals("remove")){
					JSONArray keys = new JSONArray(req.getParameter("keys"));
					
					for (int i = 0; i < keys.length(); i++) {
						repository.remove(keys.getString(i));
					}
					
				} else 
					throw new IllegalArgumentException("Illegal action");
				
				repository.close();
				
			} catch (NullPointerException npe){
				throw new IllegalArgumentException("Required argument not specified");
			} catch (JSONException e) {
				throw new IllegalArgumentException("Invalid json object");
			}
			
		} catch(Exception e){
			try {
				resp.getWriter().write((new JSONObject()).put("Error", e.getMessage()).toString());
			} catch (JSONException e1) {
				resp.getWriter().write("Kernel panic!!!");
			}
		}
		
		
	}
}
