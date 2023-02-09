import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        // Get capacity of each hospital
//        int [] capacity = new int[m];
//        for (int i = 0; i < m; i++) {
//        	st = new StringTokenizer(f.readLine());
//        	capacity[i] = Integer.parseInt(st.nextToken());
//        }
        
        
        // For each hospital i, add j mentors named i (capacity of i) to allMentor
        ArrayList<Integer> allMentors = new ArrayList<Integer>();
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(f.readLine());
        	int capacity = Integer.parseInt(st.nextToken());
        	for (int j = 0; j < capacity; j++) {
        		allMentors.add(i+1);
        	}
        }
        
        
        
        // Nested list of the preference of each hospitals
        int [][] hospitalPref = new int[m][n];
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(f.readLine());
        	for (int j=0; j<n; j++) {
        		hospitalPref[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // ArrayList of list of the preference of each hospitals
        ArrayList[] studentPref = new ArrayList[n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(f.readLine());
        	studentPref[i] = new ArrayList<>();
        	for (int j=0; j<m; j++) {
        		studentPref[i].add(Integer.parseInt(st.nextToken()));
        	}
        }
        
        // Keep track of potential student and mentor match
        int [] potentialMatch = new int[n];
        
//        // Mentors who don't have a student
//        ArrayList<Integer> freeMentors = new ArrayList<Integer>();
        
        
//        // Putting all mentors into freeMentors arraylist
//        for (int mentor=0; mentor < allMentors.size(); mentor++) {
//        	freeMentors.add(allMentors.get(mentor));
//        }
        
        // Match until no free mentors left
        // allMentors.size()>0????????
        while (!allMentors.isEmpty()) {
        	int mentor = allMentors.get(0);
        	for (int student : hospitalPref[mentor-1]) {
        		
				int currentMentor = studentPref[student-1].indexOf(potentialMatch[student-1]);
				int potentialMentor = studentPref[student-1].indexOf(mentor);
        		
        		if (potentialMatch[student-1] == 0) {
        			potentialMatch[student-1] = mentor;
        			allMentors.remove(0);
        			break;
        		}
        		
        		else if (currentMentor > potentialMentor){	
        			allMentors.add(potentialMatch[student-1]);
    				allMentors.remove(0);
    				potentialMatch[student-1] = mentor;
    				break;
        		}

        	}
        }
        for (int i=0; i<n; i++) {
        	System.out.println(potentialMatch[i]);
        }

        out.close();
    }
}
