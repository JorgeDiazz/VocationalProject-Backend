package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author JorgeDíaz
 */
public class ResponseGetSpecificJobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nameJ")
    private String name;
    @Column(name = "salary_min")
    private Double salaryMin;
    @Column(name = "salary_max")
    private Double salaryMax;
    @Column(name = "description")
    private String description;
    private int idArea;
    private String nameArea;
    private List<Career> careers;
    private List<Skill> softSkills;
    private List<Skill> hardSkills;
    private List<Process> processes;
    private List<Vacant> vacants;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public List<Skill> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<Skill> softSkills) {
        this.softSkills = softSkills;
    }

    public List<Skill> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<Skill> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public List<Vacant> getVacants() {
        return vacants;
    }

    public void setVacants(List<Vacant> vacants) {
        this.vacants = vacants;
    }

    static public class Career {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name = "nameC")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    static public class Skill {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name = "nameSK")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    static public class Process {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name = "nameD")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    static public class Vacant {

        @Column(name = "places_number")
        private int placesNumber;
        @Column(name = "start_date")
        private Date startDate;
        private List<Recruiter> recruiters;

        public int getPlacesNumber() {
            return placesNumber;
        }

        public void setPlacesNumber(int placesNumber) {
            this.placesNumber = placesNumber;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public List<Recruiter> getRecruiters() {
            return recruiters;
        }

        public void setRecruiters(List<Recruiter> recruiters) {
            this.recruiters = recruiters;
        }

        static public class Recruiter {

            @Id
            private String id;
            @Column(name = "nameP")
            private String name;
            private List<Postulant> postulants;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Postulant> getPostulants() {
                return postulants;
            }

            public void setPostulants(List<Postulant> postulants) {
                this.postulants = postulants;
            }

            static public class Postulant {

                @Id
                @Column(name = "id_person")
                private String idPerson;
                @Column(name = "nameP")
                private String name;

                public String getIdPerson() {
                    return idPerson;
                }

                public void setIdPerson(String idPerson) {
                    this.idPerson = idPerson;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

            }
        }

    }

}
