package com.Ashutosh.ReportGenerator.Service.ServiceImpl;

import com.Ashutosh.ReportGenerator.DTO.CBCDTO;
import com.Ashutosh.ReportGenerator.Entity.Result;
import com.Ashutosh.ReportGenerator.ExceptionHandler.ReportAlreadyExitsorConflict;
import com.Ashutosh.ReportGenerator.ExceptionHandler.ResourceNotFoundException;
import com.Ashutosh.ReportGenerator.Repositry.ResultRepo;
import com.Ashutosh.ReportGenerator.Service.ServiceInterface.ResultCBCServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ResultCBCServiceImpl implements ResultCBCServiceInterface {

    @Autowired
    private ResultRepo resultRepo;
    @Override
    public Result createResult(CBCDTO cbcdto) {
        Long userId = cbcdto.getUserInfo().getId();
        String reportName = "CBC";
        if (resultRepo.findByUserInfo_IdAndName(userId, reportName).isPresent()) {
            throw new ReportAlreadyExitsorConflict("Result for CBC already exists for this user.");
        }

        List<String> summary = new ArrayList<>();
        List<String> tips = new ArrayList<>();

        double hemoglobin = cbcdto.getHemoglobin();
        double rbccount=cbcdto.getRbccount();
        double packedcellvolume=cbcdto.getPackedcellvolume();
        double meancorpuscularvolume=cbcdto.getMeancorpuscularvolume();
        double mch=cbcdto.getMch();
        double mchc=cbcdto.getMchc();
        double rdw=cbcdto.getRdw();
        double wbccount = cbcdto.getWbccount();
        double neutrophils=cbcdto.getNeutrophils();
        double lymphocytces =cbcdto.getLymphocytces();
        double eosinophils=cbcdto.getEosinophils();
        double monocytes=cbcdto.getMonocytes();
        double basophils=cbcdto.getBasophils();
        double plateletcount = cbcdto.getPlateletcount();

        String gender=cbcdto.getUserInfo().getGender();

        if ("male".equalsIgnoreCase(gender)) {
            if (hemoglobin < 13 || rbccount<4.35) {
                summary.add("Low Hemoglobin or RCB Count: Potential Anemia");
                tips.add("Consider taking an iron tablet, a vitamin B12 capsule, a folic acid tablet, " +
                        "and Fercee Red syrup, and consult a doctor to treat anemia.");
            } else if (hemoglobin > 16 || rbccount>5.65) {
                summary.add("High Hemoglobin or RBC Count: This condition, called polycythemia, " +
                        "increases the risk of blood clotting in the body," +
                        " raising the likelihood of a heart attack or other related issues.");
                tips.add("Reduce the intake of iron or iron-rich foods.");
                tips.add("For the treatment of blood clotting, you can take Aspirin 75 mg " +
                        "once daily and seek medical advice if high persistently.");
            }
        } else if ("female".equalsIgnoreCase(gender)) {
            if (hemoglobin < 11.5 || rbccount<3.92) {
                summary.add("Low Hemoglobin or RCB Count: Potential Anemia");
                tips.add("Consider taking an iron tablet, a vitamin B12 capsule, a folic acid tablet, " +
                        "and Fercee Red syrup, and consult a doctor to treat anemia.");
            } else if (hemoglobin > 15.5 || rbccount>5.13) {
                summary.add("High Hemoglobin or RBC Count: This condition, called polycythemia, " +
                        "increases the risk of blood clotting in the body," +
                        " raising the likelihood of a heart attack or other related issues.");
                tips.add("Reduce the intake of iron or iron-rich foods.");
                tips.add("For the treatment of blood clotting, you can take Aspirin 75 mg " +
                        "once daily and seek medical advice if high persistently.");
            }
        }
        if (hemoglobin > 16 && plateletcount > 410000 && wbccount > 11000) {
            summary.add("Polycythemia Vera: High hemoglobin, platelet count, and WBC count indicate a myeloproliferative disorder.");
        } else if (wbccount > 11000 && neutrophils > 62 && plateletcount < 150000) {
            summary.add("Acute Bacterial Infection or Sepsis: High WBC and neutrophils with low platelets suggest severe infection or sepsis.");
        } else if (wbccount < 4000 && lymphocytces < 20 && monocytes < 2) {
            summary.add("Aplastic Anemia: Pancytopenia with low WBC, lymphocytes, and monocytes suggests bone marrow failure.");
        } else if (wbccount > 11000 && lymphocytces > 40 && monocytes > 10) {
            summary.add("Chronic Lymphocytic Leukemia (CLL): High WBC, lymphocytes, and monocytes indicate CLL.");
        } else if (wbccount > 11000 && eosinophils > 6 && basophils > 2) {
            summary.add("Chronic Myeloid Leukemia (CML) or Parasitic Infection: High WBC, eosinophils, and basophils suggest CML or a parasitic infection.");
        }

        if(packedcellvolume>50){
            summary.add("Polycythemia Vera: A bone marrow disorder leading to excessive red blood cell production.");
            summary.add("Increased Packed Cell Volume (PCV) levels may be caused by factors such as dehydration and high altitude.");
            tips.add("Increasing fluid intake to correct dehydration and informing the doctor about the condition of Polycythemia Vera.");
        }

        if(meancorpuscularvolume>101){
            summary.add("Vitamin B12 Deficiency: Often due to dietary insufficiency, pernicious anemia, or malabsorption.");
            tips.add("To address deficiencies, one can take vitamin B12 supplements and increase intake of B12-rich foods (meat, dairy) and folate-rich foods (leafy greens, fruits).");
        }

        if(wbccount>11000){
            summary.add("Increased level of WBC, known as leukocytosis, is mainly due to infection " +
                    "(bacterial, viral, fungal, or parasitic infections) or inflammation (from conditions such as rheumatoid arthritis or inflammatory bowel disease).");
            if(wbccount<12000){
                tips.add("Consider using antibiotics, antivirals, or antifungal medicines to treat the infection. " +
                        "Consult a doctor and start taking  Amoxicillin Capsule 625mg twice daily.");
            } else if (wbccount<13000) {
                tips.add("Consider using antibiotics, antivirals, or antifungal medicines to treat the infection. " +
                        "Consult a doctor and start taking Cefuroxime Tablet 500mg twice daily.");
            } else if (wbccount>13000) {
                tips.add("Consider using antibiotics, antivirals, or antifungal medicines to treat the infection. " +
                        "Consult a doctor and start taking Piperacillin and Tazobactam.");
            }
        } else if (wbccount<4000) {
            summary.add("Decrease in the level of WBC, known as leukopenia, can be due to cancer, severe infection, bone marrow disorders, or autoimmune diseases.");
            tips.add("A decrease in the level of WBC is a serious condition. We strongly advise you to seek immediate medical attention.");
        }
        if (plateletcount < 150000) {
            summary.add("Low Platelets: Which is mainly due to infection Like dengue or risk of bleeding disorders.");
            tips.add("Medications: Steroids or immune globulins to increase platelet count.Avoid injury and seek medical advice.");
        } else if (plateletcount > 410000) {
            summary.add("High Platelets: Mainly due to infection and inflammation.Potential clotting disorder.");
            tips.add("Medications: Low-dose aspirin to reduce clotting risk, hydroxyurea, or anagrelide to reduce platelet production in essential thrombocythemia.Consult a doctor for further evaluation.");
        }

        if(neutrophils<50){
            summary.add("Decreased neutrophil levels can be caused by tuberculosis," +
                    " cancer, chemotherapy, radiation therapy, bone marrow disorders, and other infections.");

        }
        else if (neutrophils>62){
            summary.add("Increase level of neutrophils which is mainly due to infection.");
        }
        if(lymphocytces>40){
            summary.add("Increased condition of lymphocytes, which may be due to microorganism infections, " +
                    "viral infections (e.g., mononucleosis), chronic lymphocytic leukemia (CLL), and possibly cancer.");
        }
        else if (lymphocytces<20){
            summary.add("Decreased lymphocyte levels are mainly due to typhoid fever and sexually transmitted diseases (HIV/AIDS).");
        }
        if(eosinophils>6){
            summary.add("Increased condition of Eosinophils which is mainly due to Parasitic infections, allergic reactions (e.g., asthma, hay fever).");
        }

        Result result = new Result();
        result.setSummary(summary);
        result.setTips(tips);
        result.setName("CBC");
        result.setUserInfo(cbcdto.getUserInfo());

         resultRepo.save(result);

        return result;

    }

    @Override
    public Result deleteResult(Long id) {
        Result result=resultRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Result not availble to delete please add CBC Report to create Result"+ id));
        resultRepo.delete(result);
        return result;
    }

    @Override
    public Result getResultbyUserID(Long id) {
        return resultRepo.findByUserInfo_Id(id);
    }
}
