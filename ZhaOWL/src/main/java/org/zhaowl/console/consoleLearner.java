package org.zhaowl.console;

import org.zhaowl.userInterface.ELInterface;
import org.zhaowl.settings.*;
public class consoleLearner {

	public   String ontologyPath;
	public   ELInterface Zha ;
	public String[] values = new String[7];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * ----- program is set to try EZ (naive teacher) by default, since console is
		 * set for ----- large ontologies, which should not use oracle skills at all 7
		 * parameters args[0] = ontology path args[1:6] = learner skills 
		 * [1] = decompose left 
		 * [2] = branch left 
		 * [3] = unsaturate left 
		 * [4] = decompose right 
		 * [5] = merge right 
		 * [6] = saturate right
		 * 
		 * Oracle skills as follows
		 * [7] = merge left
		 * [8] = saturate left
		 * [9] = branch right
		 * [10] = unsaturate right
		 *
		 * ----- OUTPUT aside from some console metrics (number of equivalence queries
		 * and some other info) a new ontology file will be created in the folder of
		 * ontology input this new ontology will be the hypothesis learned by the
		 * program
		 * 
		 */
		
		consoleLearner maker = new consoleLearner();  
		maker.doIt(args);
		

	}
	  
	
	
	public   void doIt(String[] args) {  
		
		
		try {
			
			Zha = new ELInterface();
			
			// we will load from console
			Zha.fileLoad.setSelected(true);
			
			// ontology from parameters
			Zha.filePath.setText(args[0]);
			
			// set ez mode
			Zha.ezBox.setSelected(true);
			
			// set auto learn
			Zha.autoBox.setSelected(true);
			
			// setLearnerSkills
			setLearnerSkills(args);
			
			// setLearnerSkills
			setOracleSkills(args);
			// load ontology
			Zha.loadOntology();
			
			
			
			try {
				Zha.timeStart = System.currentTimeMillis();
				Zha.learner();
				System.out.println("Total membership queries: " + Zha.membCount); 
				System.out.println("Total equivalence queries: " + Zha.equivCount);
				System.out.println("Target TBox logical axioms: " + Zha.axiomsT.size());
				Zha.showCIT(Zha.axiomsT);
				System.out.println("Hypothesis TBox logical axioms: " + Zha.ontologyH.getAxioms().size()); 
				Zha.showCIH(Zha.ontologyH.getAxioms());
				
			} catch (Throwable e) {
				System.out.println("error in learner call ----- " + e);
			}
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("error in doIt --- " + e);
		}
	
	}

	public void setOracleSkills(String[] args)
	{
		if (args[7].equals("t"))
			Zha.oracleMerge.setSelected(true);
		else
			Zha.oracleMerge.setSelected(false);

		if (args[8].equals("t"))
			Zha.oracleSaturate.setSelected(true);
		else
			Zha.oracleSaturate.setSelected(false);

		if (args[9].equals("t"))
			Zha.oracleBranch.setSelected(true);
		else
			Zha.oracleBranch.setSelected(false);

		if (args[10].equals("t"))
			Zha.oracleUnsaturate.setSelected(true);
		else
			Zha.oracleUnsaturate.setSelected(false);
	}
	
	public   void setLearnerSkills(String[] args) {
		if (args[1].equals("t"))
			Zha.learnerDecompL.setSelected(true);
		else
			Zha.learnerDecompL.setSelected(false);

		if (args[2].equals("t"))
			Zha.learnerBranch.setSelected(true);
		else
			Zha.learnerBranch.setSelected(false);

		if (args[3].equals("t"))
			Zha.learnerUnsat.setSelected(true);
		else
			Zha.learnerUnsat.setSelected(false);

		if (args[4].equals("t"))
			Zha.learnerDecompR.setSelected(true);
		else
			Zha.learnerDecompR.setSelected(false);

		if (args[5].equals("t"))
			Zha.learnerMerge.setSelected(true);
		else
			Zha.learnerMerge.setSelected(false);

		if (args[6].equals("t"))
			Zha.learnerSat.setSelected(true);
		else
			Zha.learnerSat.setSelected(false);

	}
}