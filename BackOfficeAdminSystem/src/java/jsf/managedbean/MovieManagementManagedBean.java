/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.MovieSessionBeanLocal;
import entity.CinemaEntity;
import entity.MovieEntity;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import util.exception.CinemaNotFoundException;
import util.exception.CreateNewMovieException;
import util.exception.DeleteCinemaException;
import util.exception.DeleteMovieException;
import util.exception.MovieNotFoundException;

/**
 *
 * @author shaunphua
 */
@Named(value = "movieManagementManagedBean")
@SessionScoped
public class MovieManagementManagedBean implements Serializable {

    @EJB
    private MovieSessionBeanLocal movieSessionBean;
    
    private List<MovieEntity> movieEntities;
    private List<MovieEntity> filteredMovieEntities;
    private MovieEntity newMovieEntity;
    private MovieEntity selectedMovieEntityToView;
    private MovieEntity selectedMovieEntityToUpdate;
    
    private List<Integer> prices;
    private List<String> ratings;
   
    public MovieManagementManagedBean() {
        movieEntities= new ArrayList<>();
        filteredMovieEntities = new ArrayList<>();
        newMovieEntity = new MovieEntity();
        prices = new ArrayList<>();
        prices.add(10);
        prices.add(15);
        ratings = new ArrayList<>();
        ratings.add("G");
        ratings.add("PG");
        ratings.add("PG13");
        ratings.add("NC16");
        ratings.add("M18");
        ratings.add("R21");
        selectedMovieEntityToUpdate = new MovieEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        setMovieEntities(getMovieSessionBean().retrieveAllMovieEntities());
        for (MovieEntity me: getMovieEntities()) {
            getFilteredMovieEntities().add(me);
        }
    }
    
    public void viewMovieDetails(ActionEvent event) throws IOException
    {
        Long movieIdToView = (Long)event.getComponent().getAttributes().get("movieId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("movieIdToView", movieIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewProductDetails.xhtml");
    }
    
    public void createNewMovie(ActionEvent event)
    {
        try
        {
            MovieEntity me = getMovieSessionBean().createNewMovie(getNewMovieEntity());
            getMovieEntities().add(me);
            getFilteredMovieEntities().add(me);
            setNewMovieEntity(new MovieEntity());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New movie created successfully (Movie ID: " + me.getMovieId()+ ")", null));
        }
        catch(CreateNewMovieException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new movie: " + ex.getMessage(), null));
        }
    }
    
    public void updateMovie(ActionEvent event)
    {
        try
        {
            getMovieSessionBean().updateMovie(getSelectedMovieEntityToUpdate());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Movie updated successfully", null));
        }
        catch(MovieNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating movie: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void deleteMovie(ActionEvent event)
    {
        try
        {
            
            MovieEntity movieEntityToDelete = (MovieEntity)event.getComponent().getAttributes().get("movieEntityToDelete");
            
            getMovieSessionBean().deleteMovie(movieEntityToDelete.getMovieId());
            
            getMovieEntities().remove(movieEntityToDelete);
            getFilteredMovieEntities().remove(movieEntityToDelete);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Movie deleted successfully", null));
        }
        catch(MovieNotFoundException | DeleteMovieException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting movie: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public MovieSessionBeanLocal getMovieSessionBean() {
        return movieSessionBean;
    }

    public void setMovieSessionBean(MovieSessionBeanLocal movieSessionBean) {
        this.movieSessionBean = movieSessionBean;
    }

    public List<MovieEntity> getMovieEntities() {
        return movieEntities;
    }

    public void setMovieEntities(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
    }

    public List<MovieEntity> getFilteredMovieEntities() {
        return filteredMovieEntities;
    }

    public void setFilteredMovieEntities(List<MovieEntity> filteredMovieEntities) {
        this.filteredMovieEntities = filteredMovieEntities;
    }

    public MovieEntity getNewMovieEntity() {
        return newMovieEntity;
    }

    public void setNewMovieEntity(MovieEntity newMovieEntity) {
        this.newMovieEntity = newMovieEntity;
    }

    public MovieEntity getSelectedMovieEntityToView() {
        return selectedMovieEntityToView;
    }

    public void setSelectedMovieEntityToView(MovieEntity selectedMovieEntityToView) {
        this.selectedMovieEntityToView = selectedMovieEntityToView;
    }

    public MovieEntity getSelectedMovieEntityToUpdate() {
        return selectedMovieEntityToUpdate;
    }

    public void setSelectedMovieEntityToUpdate(MovieEntity selectedMovieEntityToUpdate) {
        this.selectedMovieEntityToUpdate = selectedMovieEntityToUpdate;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public void setPrices(List<Integer> prices) {
        this.prices = prices;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }
    
}
