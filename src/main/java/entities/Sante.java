package entities;

import javafx.scene.control.SingleSelectionModel;

public class Sante  {
        int id;
        String maladie,medicament;
        int calories;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMaladie() {
            return maladie;
        }

        public void setMaladie(String maladie) {
            this.maladie = maladie;
        }

        public String getMedicment() {
            return medicament;
        }

        public void setMedicment(String medicament) {
            this.medicament = medicament;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }
        public Sante(){}
        public Sante(int id,String maladie,String medicament,int calories)
        {
            this.id=id;
            this.maladie=maladie;
            this.medicament=medicament;
            this.calories=calories;

        }
        public Sante(String maladie,String medicament,int calories)
        {

            this.maladie=maladie;
            this.medicament=medicament;
            this.calories=calories;

        }
        public String toString() {
            return


                    "Maladies=" + maladie

                    ;
        }
    }


