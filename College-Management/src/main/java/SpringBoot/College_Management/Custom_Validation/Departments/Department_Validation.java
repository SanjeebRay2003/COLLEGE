package SpringBoot.College_Management.Custom_Validation.Departments;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class Department_Validation implements ConstraintValidator<Validate_Department,String> {
    @Override
    public boolean isValid(String department, ConstraintValidatorContext constraintValidatorContext) {
        if (department == null) return false;
        List<String> roles = List.of("ENGINEERING AND TECHNOLOGY","MANAGEMENT","LAW","PHARMACY","BASIC AND APPLIED SCIENCE",
                "CIVIL ENGINEERING", "DEPARTMENT OF MECHANICAL ENGINEERING",
                "ELECTRICAL ENGINEERING", "ELECTRONICS ENGINEERING",
                "COMPUTER ENGINEERING", "AERONAUTICAL ENGINEERING",
                "AUTOMOBILE ENGINEERING", "MARINE ENGINEERING",
                "BIOMEDICAL ENGINEERING", "CHEMICAL ENGINEERING",
                "INDUSTRIAL ENGINEERING", "MECHATRONICS",
                "MINING ENGINEERING", "PETROLEUM ENGINEERING",
                "TEXTILE ENGINEERING", "INFORMATION TECHNOLOGY",
                "COMPUTER SCIENCE & ENGINEERING", "ARTIFICIAL INTELLIGENCE & DATA SCIENCE",
                "CYBER SECURITY & FORENSICS", "INTERNET OF THINGS (IOT)",
                "BLOCKCHAIN TECHNOLOGY", "CLOUD COMPUTING",
                "ROBOTICS & AUTOMATION", "SOFTWARE ENGINEERING",
                "BIOINFORMATICS", "GAME DEVELOPMENT TECHNOLOGY",
                "AUGMENTED & VIRTUAL REALITY",  "DATA ANALYTICS",
                "EMBEDDED SYSTEMS", "DIGITAL COMMUNICATION",
                "BUSINESS ADMINISTRATION", "ACCOUNTING & FINANCE",
                "ECONOMICS", "MARKETING", "HUMAN RESOURCE MANAGEMENT",
                "ENTREPRENEURSHIP", "BANKING & INSURANCE",
                "TOURISM & HOSPITALITY MANAGEMENT", "LAW",
                "POLITICAL SCIENCE", "PUBLIC ADMINISTRATION",
                "INTERNATIONAL RELATIONS", "SOCIOLOGY",
                "PSYCHOLOGY", "MASS COMMUNICATION", "JOURNALISM",
                "ENGLISH LITERATURE", "HISTORY", "PHILOSOPHY",
                "FINE ARTS", "MUSIC", "FILM & MEDIA STUDIES",
                "MATHEMATICS", "PHYSICS", "CHEMISTRY",
                "BIOLOGY", "BIOTECHNOLOGY", "ENVIRONMENTAL SCIENCE",
                "MICROBIOLOGY", "GEOLOGY", "PHARMACY",
                "NURSING", "MEDICAL LAB TECHNOLOGY", "RADIOLOGY & IMAGING",
                "DENTISTRY", "MEDICINE & SURGERY", "VETERINARY SCIENCE",
                "AGRICULTURAL SCIENCE", "HORTICULTURE", "FISHERIES SCIENCE",
                "FOOD TECHNOLOGY", "SPORTS SCIENCE", "PHYSICAL EDUCATION",
                "AVIATION MANAGEMENT", "MARINE TRANSPORTATION",
                "NAVAL ARCHITECTURE", "FASHION DESIGN", "INTERIOR DESIGN",
                "ARCHITECTURE", "URBAN PLANNING", "GRAPHIC DESIGN",
                "ANIMATION & MULTIMEDIA", "EVENT MANAGEMENT",
                "LIBRARY & INFORMATION SCIENCE");
        return roles.contains(department);
    }
}
