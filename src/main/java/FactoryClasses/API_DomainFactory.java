package FactoryClasses;

import Constants.ApiDomain;

public class API_DomainFactory {

    public static String getGlobalSearchDomain(String environment){

        switch(environment.toLowerCase()){
            case "dev":
            case "qa": return ApiDomain.GLOBAL_SEARCH_SERVICE_DEV;
            case "uat": return ApiDomain.GLOBAL_SEARCH_SERVICE_UAT;
            case "beta":
            case "prod": return ApiDomain.GLOBAL_SEARCH_SERVICE_PROD;
            case "uataws": return ApiDomain.AWS_GLOBAL_SEARCH_SERVICE_UAT;
            case "prodaws": return ApiDomain.AWS_GLOBAL_SEARCH_SERVICE_PROD;
            default : return null;
        }

    }

    public static String getResearchAnalysisDomain(String environment){

        switch(environment.toLowerCase()){
            case "dev":
            case "qa":return ApiDomain.RESEARCH_ANALYSIS_SERVICE_DEV;
            case "uat": return ApiDomain.RESEARCH_ANALYSIS_SERVICE_UAT;
            case "beta":
            case "prod": return ApiDomain.RESEARCH_ANALYSIS_SERVICE_PROD;
            case "uataws": return ApiDomain.AWS_RESEARCH_SERVICE_UAT;
            case "prodaws": return ApiDomain.AWS_RESEARCH_SERVICE_PROD;
            default : return null;
        }

    }


    public static String getCompanySourcingDomain(String environment){

        switch(environment.toLowerCase()){
            case "dev":return ApiDomain.GLOBAL_SEARCH_SERVICE_DEV;
            case "uat": return ApiDomain.GLOBAL_SEARCH_SERVICE_UAT;
            case "prod": return ApiDomain.GLOBAL_SEARCH_SERVICE_PROD;
            case "uataws": return ApiDomain.AWS_RESEARCH_SERVICE_UAT;
            case "prodaws": return ApiDomain.AWS_RESEARCH_SERVICE_PROD;
            default : return null;
        }

    }

    public static String getAuthDomain(String environment){
        switch (environment.toLowerCase()){
            case "uat": return ApiDomain.AUTHENTICATION_SERVICE_UAT;
            case "prod": return ApiDomain.AUTHENTICATION_SERVICE_PROD;
            case "uataws":
            case "prodaws": return ApiDomain.AWS_AUTH_API_UAT;
            default : return null;
        }
        }

        public static String getScreenerDataTableDomain(String environment){

            switch(environment.toLowerCase()){
                case "dev": return ApiDomain.AWS_RESEARCH_SERVICE_DEV;
                case "uataws": return ApiDomain.AWS_RESEARCH_SERVICE_UAT;
                case "prodaws": return ApiDomain.AWS_RESEARCH_SERVICE_PROD;
                default : return null;
            }
    
        }

        public static String getOrchestrationDomain(String environment){

            switch(environment.toLowerCase()){
                case "uataws":
                case "prodaws":return ApiDomain.AWS_ORCHESTRATION_SERVICE_UAT;
                default : return null;
            }

        }




    }

