package draft1;

import java.util.ArrayList;

public interface DataManager {
	public abstract ArrayList<Applicant> loadApplicants();

	public abstract ArrayList<Application> loadApplications();

	public abstract ArrayList<Officer> loadOfficers();

	public abstract void saveApplicants();

	public abstract void saveApplications();

	public abstract void saveOfficers();
}
