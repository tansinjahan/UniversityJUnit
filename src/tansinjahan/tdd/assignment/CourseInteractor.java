package tansinjahan.tdd.assignment;

import org.apache.log4j.Logger;

import utilities.Trace;

public class CourseInteractor {
	 
	Logger logger = Trace.getInstance().getLogger(this);
	University versity;
	public CourseInteractor(University versity) {
		this.versity=versity;
	}
	public Course createCourse(
			 			String user,
			 			String title,
			 			int code,
			 			int capsize, 
			 			boolean hasAFinal, 
			 			int numberOfAssignments, 
			 			int numberOfMidTerms, 
			 			boolean enforcePrereqs,
			 			boolean isProjectCourse
			 		) {
		validateCode(code);
		validateGradeElems(hasAFinal, numberOfAssignments, numberOfMidTerms);
		validateCapsize(capsize);
		 		
		if (versity.hasCourseExists(code)) 
		 			throw new IllegalArgumentException("Course already exist");
		 		
		logger.info(String.format("Course is created with title %s and capsize %d", title, capsize));
		Course course = versity.createCourse(title, capsize,isProjectCourse);
		course.setCode(code);
		course.setHasAFinal(hasAFinal);
		course.setAssignments(numberOfAssignments);
		course.setMidterm(numberOfMidTerms);
		course.setPrerequisites(enforcePrereqs);
		
		return course;
	}
	private void validateCapsize(int capsize) {
		
		if (capsize <= 25)
			 		throw new IllegalArgumentException("capsize must be greater than 25");
		logger.info(String.format("Course is created with valid class size"));
	}
	private void validateCode(int code) {
		 		String codeStr = String.valueOf(code);
		 		if (codeStr.startsWith("0") || codeStr.length() != 6)
		 			throw new IllegalArgumentException("Code must be of length 6 and first digit cannot be zero");
		 		logger.info(String.format("Course is created with valid code"));
		 	}
	
	private void validateGradeElems(boolean hasAFinal, int numberOfAssignments, int numberOfMidTerms) {
		 		if (!hasAFinal && numberOfAssignments == 0 && numberOfMidTerms == 0)
		 			throw new IllegalArgumentException("There must be one grade element");
		 		else if(numberOfAssignments > 5 || numberOfMidTerms > 2)
		 			throw new IllegalArgumentException("Number of assignments or midterm exceeds");
		 	
		 		logger.info(String.format("Course is created with valid grade elements"));
		 	}

}
