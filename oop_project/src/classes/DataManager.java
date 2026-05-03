package classes;

import java.util.ArrayList;

/**
 * DataManager interface defines the contract for loading and saving
 * data related to Applicants, Applications, and Officers.
 * 
 * Any class implementing this interface must provide functionality
 * for reading from and writing to a data source (e.g., CSV files).
 * 
 * @author Hashir
 * @version 1.0
 */
public interface DataManager {

    /**
     * Loads applicant data from a data source.
     * 
     * @return a list of Applicant objects
     */
    public abstract ArrayList<Applicant> loadApplicants();

    /**
     * Loads application data from a data source.
     * 
     * @return a list of Application objects
     */
    public abstract ArrayList<Application> loadApplications();

    /**
     * Loads officer data from a data source.
     * 
     * @return a list of Officer objects
     */
    public abstract ArrayList<Officer> loadOfficers();

    /**
     * Saves applicant data to a data source.
     */
    public abstract void saveApplicants();

    /**
     * Saves application data to a data source.
     */
    public abstract void saveApplications();

    /**
     * Saves officer data to a data source.
     */
    public abstract void saveOfficers();
}