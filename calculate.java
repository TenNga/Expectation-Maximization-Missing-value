import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 10ngawang on 2017-11-10.
 */
public class calculate {
    double probGenderZero=0.7;
    double probGenderOne = 0.3;
    ///Probability table Weight Given Gender P(W|G)
    double probWeightZeroGenderZero=0.8;
    double probWeighOneGenderZero = 0.2;
    double probWeighZeroGenderOne = 0.4;
    double probWeighOneGenderOne = 0.6;
    //Probability table P(H|G)
    double probHeightZeroGenderZero = 0.7;
    double probHeightOneGenderZero = 0.3;
    double probHeightZeroGenderOne = 0.3;
    double probHeightOneGenderOne = 0.7;

    double zeroZeroZeroProb=0; //000
    double oneZeroZeroProb=0;  //100
    double ZeroOneZeroProb=0;  //010
    double ZeroZeroOneProb=0;  //001
    double OneOneZeroProb=0;   //110
    double ZeroOneOneProb=0;   //011
    double oneZeroOneProb=0;   //101
    double oneOneOneProb=0;    //111

    double newThreshold=0;
    double oldThreshold;
    public void findP(LinkedList<ArrayList<Integer>> data,int x) {
        //Probability table P(Gender)


        if(x>0){
            System.out.println("<///////////////RAMDOM STARTING POINT/////////////////////>");
            probGenderZero=Math.random();
            probGenderOne=1-probGenderZero;
            probWeightZeroGenderZero = Math.random();
            probWeighOneGenderZero = 1-probWeightZeroGenderZero;
            probWeighZeroGenderOne = Math.random();
            probWeighOneGenderOne =1-probWeighZeroGenderOne;
            probHeightZeroGenderZero = Math.random();
            probHeightOneGenderZero = 1-probHeightZeroGenderZero;
            probHeightZeroGenderOne = Math.random();
            probHeightOneGenderOne = 1-probHeightZeroGenderOne;
        }

        printStartingPoint();

//        newThreshold=Math.abs(Math.log(probGenderZero*probGenderOne* probWeightZeroGenderZero*probWeighOneGenderZero
//                        *probWeighZeroGenderOne*probWeighOneGenderOne*probHeightZeroGenderZero*probHeightOneGenderZero
//                            *probHeightZeroGenderOne*probHeightOneGenderOne));
      //  System.out.println("First Threshold: "+newThreshold);
        int iteration=0;

    //   while((Math.abs(newThreshold-oldThreshold) ) > 0.001) {
           do{
           iteration++;

       //for(int x=0;x<30;x++){
          // System.out.println("THRESHOLD: "+Math.abs(newThreshold-oldThreshold)+"\n");

            oldThreshold = newThreshold;
            newThreshold=0;

            for (int i = 0; i < data.size(); i++) {
               // System.out.println("Data Size: "+data.size());

                calcLikelihood(data.get(i));  //calculating Likelihood


                if (ZZZ(data.get(i))) {
                    zeroZeroZeroProb++;
                   // System.out.println("pre ZZZ: "+zeroZeroZeroProb);
                } else if (OZZ(data.get(i))) {
                    oneZeroZeroProb++;
                } else if (ZOZ(data.get(i))) {
                    ZeroOneZeroProb++;
                } else if (ZZO(data.get(i))) {
                    ZeroZeroOneProb++;
                } else if (OOZ(data.get(i))) {
                    OneOneZeroProb++;
                } else if (ZOO(data.get(i))) {
                    ZeroOneOneProb++;
                } else if (OZO(data.get(i))) {
                    oneZeroOneProb++;
                } else if (OOO(data.get(i))) {
                    oneOneOneProb++;
                } else if (Missing(data.get(i))) {
                  //  System.out.println("Missing!!!!");
                    double result;


                    data.get(i).set(0, 0);// if missing is ZERO

                    if (data.get(i).get(0) == 0 && data.get(i).get(1) == 0 && data.get(i).get(2) == 0) {
                        double nominator = probGenderZero * probWeightZeroGenderZero * probHeightZeroGenderZero;
                        double denominator = (probGenderZero * probWeightZeroGenderZero * probHeightZeroGenderZero) + (probGenderOne * probWeighZeroGenderOne * probHeightZeroGenderOne);
                        result = (nominator / denominator);
                        zeroZeroZeroProb = zeroZeroZeroProb + result;
                       // System.out.println("ZZZ: "+zeroZeroZeroProb);
                        oneZeroZeroProb = oneZeroZeroProb + (1 - result);

                    } else if (data.get(i).get(0) == 0 && data.get(i).get(1) == 0 && data.get(i).get(2) == 1) {
                        double nominator = probGenderZero * probWeightZeroGenderZero * probHeightOneGenderZero;
                        double denominator = (probGenderZero * probWeightZeroGenderZero * probHeightOneGenderZero) + (probGenderOne * probWeighZeroGenderOne * probHeightOneGenderOne);
                        result = (nominator / denominator);
                        ZeroZeroOneProb = ZeroZeroOneProb + result;
                        oneZeroOneProb = oneZeroOneProb + (1 - result);

                    } else if (data.get(i).get(0) == 0 && data.get(i).get(1) == 1 && data.get(i).get(2) == 0) {
                        double nominator = probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero;
                        double denominator = (probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero) + (probGenderOne * probWeighOneGenderOne * probHeightZeroGenderOne);
                        result = (nominator / denominator);
                        ZeroOneZeroProb = ZeroOneZeroProb + result;
                        OneOneZeroProb = OneOneZeroProb + (1 - result);

                    } else if (data.get(i).get(0) == 0 && data.get(i).get(1) == 1 && data.get(i).get(2) == 1) {
                        double nominator = probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero;
                        double denominator = (probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero) + (probGenderOne * probWeighOneGenderOne * probHeightOneGenderOne);
                        result = (nominator / denominator);
                        ZeroOneOneProb = ZeroOneOneProb + result;
                        oneOneOneProb = oneOneOneProb + (1 - result);
                    }
                    data.get(i).set(0,2);

                } // if inner
            } // for loop end
               System.out.println("Threshold= "+newThreshold);


//            System.out.println("G W H  TotalProbability");
//            // System.out.println("-------------------------");
//            System.out.println("0 0 0: " + zeroZeroZeroProb);
//            System.out.println("1 0 0: " + oneZeroZeroProb);
//            System.out.println("0 1 0: " + ZeroOneZeroProb);
//            System.out.println("0 0 1: " + ZeroZeroOneProb);
//            System.out.println("1 1 0: " + OneOneZeroProb);
//            System.out.println("0 1 1: " + ZeroOneOneProb);
//            System.out.println("1 0 1: " + oneZeroOneProb);
//            System.out.println("1 1 1: " + oneOneOneProb);
//            System.out.println();

            //// Probability Updates====================================>>>>>>>>>>>>>
            calProb();  //Update probability



            zeroZeroZeroProb=0; //000
            oneZeroZeroProb=0;  //100
            ZeroOneZeroProb=0;  //010
            ZeroZeroOneProb=0;  //001
            OneOneZeroProb=0;   //110
            ZeroOneOneProb=0;   //011
            oneZeroOneProb=0;   //101 
            oneOneOneProb=0;    //111
             //  System.out.println("---Plot Threshold: "+Math.abs(newThreshold-oldThreshold));
        }while (Math.abs(newThreshold-oldThreshold) > 0.001);
           printProb();  // Printing the update table
        System.out.println("------> Threshold Reached:====>  "+(Math.abs(newThreshold-oldThreshold)));
        System.out.println("------> Iteration: "+iteration);
    }// class end

    public boolean ZZZ(ArrayList<Integer> x){
        int i=0;
            if(x.get(i)==0 && x.get(i+1)==0 && x.get(i+2)==0)
                return true;
            else
                return false;
    }
    public boolean OZZ(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==1 && x.get(i+1)==0 && x.get(i+2)==0)
            return true;
        else
            return false;
    }
    public boolean ZOZ(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==0 && x.get(i+1)==1 && x.get(i+2)==0)
            return true;
        else
            return false;
    }
    public boolean ZZO(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==0 && x.get(i+1)==0 && x.get(i+2)==1)
            return true;
        else
            return false;
    }
    public boolean OOZ(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==1 && x.get(i+1)==1 && x.get(i+2)==0)
            return true;
        else
            return false;
    }
    public boolean ZOO(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==0 && x.get(i+1)==1 && x.get(i+2)==1)
            return true;
        else
            return false;
    }
    public boolean OZO(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==1 && x.get(i+1)==0 && x.get(i+2)==1)
            return true;
        else
            return false;
    }
    public boolean OOO(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==1 && x.get(i+1)==1 && x.get(i+2)==1)
            return true;
        else
            return false;
    }
    public boolean Missing(ArrayList<Integer> x){
        int i=0;
        if(x.get(i)==2)
            return true;
        else
            return false;
    }

    public void calcLikelihood (ArrayList<Integer> data){
        double result;

        if (Missing(data)) { // if missing value is G =0
            data.set(0, 0);// if missing is ZERO

            if (data.get(0) == 0 && data.get(1) == 0 && data.get(2) == 0) {  // When G=0 with other combination

                double denominator = (probGenderZero * probWeightZeroGenderZero * probHeightZeroGenderZero) + (probGenderOne * probWeighZeroGenderOne * probHeightZeroGenderOne);
                result = (denominator);
                newThreshold += Math.abs(Math.log(result));
            } else if (data.get(0) == 0 && data.get(1) == 0 && data.get(2) == 1) {
                double denominator = (probGenderZero * probWeightZeroGenderZero * probHeightOneGenderZero) + (probGenderOne * probWeighZeroGenderOne * probHeightOneGenderOne);
                result = ( denominator);
                newThreshold += Math.abs(Math.log(result));
            } else if (data.get(0) == 0 && data.get(1) == 1 && data.get(2) == 0) {
                double denominator = (probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero) + (probGenderOne * probWeighOneGenderOne * probHeightZeroGenderOne);
                result = ( denominator);
                newThreshold += Math.abs(Math.log(result));
            } else if (data.get(0) == 0 && data.get(1) == 1 && data.get(2) == 1) {
                double denominator = (probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero) + (probGenderOne * probWeighOneGenderOne * probHeightOneGenderOne);
                result = (denominator);
                newThreshold += Math.abs(Math.log(result));
            }
            data.set(0,2);

            



        // if data set is not missing
        }else if (ZZZ(data)) {
            newThreshold += Math.log(probGenderZero * probWeightZeroGenderZero * probHeightZeroGenderZero);

        } else if (OZZ(data)) {
            newThreshold += Math.log(probGenderOne * probWeighZeroGenderOne * probHeightZeroGenderOne);

        } else if (ZOZ(data)) {
            newThreshold += Math.log(probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero);

        } else if (ZZO(data)) {
            newThreshold += Math.log(probGenderZero * probWeightZeroGenderZero * probHeightOneGenderZero);

        } else if (OOZ(data)) {
            newThreshold += Math.log(probGenderOne * probHeightZeroGenderOne * probWeighOneGenderOne);

        } else if (ZOO(data)) {
            newThreshold += Math.log(probGenderZero * probWeighOneGenderZero * probHeightOneGenderZero);

        } else if (OZO(data)) {
            newThreshold += Math.log(probGenderOne * probHeightOneGenderOne * probWeighZeroGenderOne);

        } else if (OOO(data)) {
            newThreshold += Math.log(probGenderOne * probWeighOneGenderOne * probHeightOneGenderOne);
        }

    }

    public void calProb(){
        //P(G=0)
        // oldProb = probGenderZero;
        probGenderZero = (zeroZeroZeroProb + ZeroOneZeroProb + ZeroZeroOneProb + ZeroOneOneProb) / 20;
        //  newThreshold = newThreshold +Math.abs(Math.log(probGenderZero));//-(Math.log(oldProb)));
        // System.out.println("Threshold= "+Math.log10(probGenderZero));

        //P(G=1)
        //  oldProb = probGenderOne;
        probGenderOne = (oneZeroZeroProb + OneOneZeroProb + oneZeroOneProb + oneOneOneProb) / 20;
        //System.out.println("New Gender = 1: " + probGenderOne);
        //newThreshold = newThreshold+Math.abs(Math.log(probGenderOne));//-(Math.log(oldProb)));
        // System.out.println("Threshold= "+newThreshold);

        //P(W=0 | G=0)
        //  oldProb = probWeightZeroGenderZero;
        probWeightZeroGenderZero = ((zeroZeroZeroProb + ZeroZeroOneProb) / (zeroZeroZeroProb + ZeroOneZeroProb + ZeroZeroOneProb + ZeroOneOneProb));
       // System.out.println("New Weight = 0 | Gender = 0: " + probWeightZeroGenderZero);
        // newThreshold = newThreshold+Math.abs(Math.log(probWeightZeroGenderZero));//-(Math.log(oldProb)));
        // System.out.println("Threshold= "+newThreshold);

        //P(W=1 | G=0)
        //  oldProb = probWeighOneGenderZero;
        probWeighOneGenderZero = ((ZeroOneZeroProb + ZeroOneOneProb) / (zeroZeroZeroProb + ZeroOneZeroProb + ZeroZeroOneProb + ZeroOneOneProb));
       // System.out.println("New Weight = 1 | Gender = 0: " + probWeighOneGenderZero);
        // newThreshold = newThreshold+Math.abs(Math.log(probWeighOneGenderZero));//-(Math.log(oldProb)));
        //System.out.println("Threshold= "+newThreshold);

        //P(W=0 | G=1)
        //  oldProb = probWeighZeroGenderOne;
        probWeighZeroGenderOne = ((oneZeroZeroProb + oneZeroOneProb) / (oneZeroZeroProb + OneOneZeroProb + oneZeroOneProb + oneOneOneProb));
       // System.out.println("New Weight = 0 | Gender = 1: " + probWeighZeroGenderOne);
        // newThreshold = newThreshold +Math.abs(Math.log(probWeighZeroGenderOne));//-(Math.log(oldProb)));
        // System.out.println("Threshold= "+newThreshold);

        //P(W=1 | G=1)
        // oldProb=probWeighOneGenderOne;
        probWeighOneGenderOne = ((OneOneZeroProb + oneOneOneProb) / (oneZeroZeroProb + OneOneZeroProb + oneZeroOneProb + oneOneOneProb));
       // System.out.println("New Weight = 1 | Gender = 1: " + probWeighOneGenderOne);
        //  newThreshold = newThreshold+Math.abs(Math.log(probWeighOneGenderOne));//-(Math.log(oldProb)));
        //System.out.println("Threshold= "+newThreshold);

        //P(H=0 | G=0)
        //  oldProb=probHeightZeroGenderZero;
        probHeightZeroGenderZero = ((zeroZeroZeroProb + ZeroOneZeroProb) / (zeroZeroZeroProb + ZeroOneZeroProb + ZeroZeroOneProb + ZeroOneOneProb));
       // System.out.println("New Height =0 | Gender =0: " + probHeightZeroGenderZero);
        // newThreshold=newThreshold+Math.abs(Math.log(probHeightZeroGenderZero));//-(Math.log(oldProb)));
        // System.out.println("Threshold= "+newThreshold);

        //P(H=1 | G=0)
        // oldProb=probHeightOneGenderZero;
        probHeightOneGenderZero = ((ZeroZeroOneProb + ZeroOneOneProb) / (zeroZeroZeroProb + ZeroOneZeroProb + ZeroZeroOneProb + ZeroOneOneProb));
       // System.out.println("New Height = 1 | Gender = 0: " + probHeightOneGenderZero);
        // newThreshold=newThreshold+Math.abs(Math.log(probHeightOneGenderZero));//-(Math.log(oldProb)));
        // System.out.println("Threshold= "+newThreshold);

        //P(H=0 | G=1)
        //  oldProb=probHeightZeroGenderOne;
        probHeightZeroGenderOne = ((oneZeroZeroProb + OneOneZeroProb) / (oneZeroZeroProb + OneOneZeroProb + oneZeroOneProb + oneOneOneProb));
      //  System.out.println("New Height = 0 | Gender = 1: " + probHeightZeroGenderOne);
        // newThreshold=newThreshold+Math.abs(Math.log(probHeightZeroGenderOne));//-(Math.log(oldProb)));
        /// System.out.println("Threshold= "+newThreshold);

        //P(H=1 | G=1)
        //oldProb=probHeightOneGenderOne;
        probHeightOneGenderOne = ((oneZeroOneProb + oneOneOneProb) / (oneZeroZeroProb + OneOneZeroProb + oneZeroOneProb + oneOneOneProb));
      //  System.out.println("New Height = 1 | Gender = 1: " + probHeightOneGenderOne);
        // newThreshold=newThreshold+Math.abs(Math.log(probHeightOneGenderOne));//-(Math.log(oldProb)));

    }
    public void printProb(){
        //P(G=0)
        System.out.println("New Gender = 0: " + probGenderZero);

        //P(G=1)
        System.out.println("New Gender = 1: " + probGenderOne);

        //P(W=0 | G=0)
        System.out.println("New Weight = 0 | Gender = 0: " + probWeightZeroGenderZero);

        //P(W=1 | G=0)
        System.out.println("New Weight = 1 | Gender = 0: " + probWeighOneGenderZero);

        //P(W=0 | G=1)
        System.out.println("New Weight = 0 | Gender = 1: " + probWeighZeroGenderOne);

        //P(W=1 | G=1)
        System.out.println("New Weight = 1 | Gender = 1: " + probWeighOneGenderOne);

        //P(H=0 | G=0)
        System.out.println("New Height =0 | Gender =0: " + probHeightZeroGenderZero);

        //P(H=1 | G=0)
        System.out.println("New Height = 1 | Gender = 0: " + probHeightOneGenderZero);

        //P(H=0 | G=1)
        System.out.println("New Height = 0 | Gender = 1: " + probHeightZeroGenderOne);

        //P(H=1 | G=1)
        System.out.println("New Height = 1 | Gender = 1: " + probHeightOneGenderOne);

        System.out.println();
    }

    public void printStartingPoint(){
        System.out.println("<------:STARTING POINT:----->");
        System.out.println("probGenderZero = "+probGenderZero);
        System.out.println("probGenderOne = "+probGenderOne);
        System.out.println("probWeightZeroGenderZero = "+probWeightZeroGenderZero);
        System.out.println("probWeighOneGenderZero = " + probWeighOneGenderZero);
        System.out.println("probWeighZeroGenderOne = " + probWeighZeroGenderOne);
        System.out.println("probWeighOneGenderOne = " + probWeighOneGenderOne);
        System.out.println("probHeightZeroGenderZero = " + probHeightZeroGenderZero);
        System.out.println("probHeightOneGenderZero = " + probHeightOneGenderZero);
        System.out.println("probHeightZeroGenderOne = " + probHeightZeroGenderOne);
        System.out.println("probHeightOneGenderOne = " + probHeightOneGenderOne);
        System.out.println();
    }

//    public double findProb(ArrayList<Integer> x){
//        double result =0;
//        int[] data = new int[3];
//        for(int i=0;i<x.size();i++){
//            data[i]=x.get(i);
//        }
//        // if missing is ZERO
//        data[0]=0;
//        if(data[0]==0&& data[1]==0 && data[2]==0){
//            result=probGenderZero;
//        }
//    }
}
