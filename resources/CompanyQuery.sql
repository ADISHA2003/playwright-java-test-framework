select companyid , sc_code,companyname , previous_name , dba , yearfounded , numberofemployees , companytypeid , m1.description as "companyTypeFromMaster"  , launchdate , fundstatus , m2.description as "fundStatusFromMaster", fundsize , companystatusid , m3.description as "companyStatusFromMaster",registered_address_flag , primaryaddressline1 , primaryaddressline2 , primaryaddressline3 , c.countryid , c2.countryname as "countryNameFromCoutryTable", c.stateid , s.statename as "stateNameFromStateTable", city , tier , pincode , phonenumber1 , phonenumber2 , faxnumber , emailid , businessdescription , c.directoryfeature , m4.description as "directoryFeatureFromMaster", c.industry_group_id ,ig.industry_group_name as "industryGroupNameFromIndustryGroupTable",c.industry_id , i.industry_name as "industryFromIndustryTable" ,c.sub_industry_id , si.sub_industry_name as "subIndustryFromSubIndustryTable", website , district , closing_date , sponsor , 
minimum , maximum, stage_investment, m9.description as "stageInvestmentFromMaster" , c.areaofintrest ,c.`global` , g.globalname as "globalFromGlobalTable",
c.continent , c3.continentname as "ContinentNameFromContinentTable",c.subcontinent , isc.subcontinentname as "subContinentFromIndiaSubContinentTable",
c.specialization , c.deal_types , c.description , c.financial_advisor , c.legal_advisor , c.deal_value , c.stake_value , c.source , c.funding_recieved , c.market_news , c.created , c.updated ,c.mother_child_fund , c.seed_incubation , c.venture_capital ,c.private_equity , c.investor_equity , c.corporate_identity_number , c.india_coverage , c.funding_status , m5.description as "fundingStatusFromMaster",c.investor_type , m6.description as "investorTypeFromMaster",c.fund_type , c.vccedge_ic , vi.vccedge_ic as "vccedge_icFromVccedge_icTable", c.MCA_status , c.investment_type , c.domicile , c.denomination ,c.sector_tags , c.mobile_number ,c.company_logo , c.primary_exchange ,m7.description as "primaryExchangeFromMaster", c.latitude , c.longitude , c.short_description , c.sub_sector_tags , c.sector_theme , c.business_model , c.category_coverage , c.logo_height , c.logo_width , c.mcache_company_id ,
c.incorporationdate , c.fund_registration_id , c.financial_added , c.financial_validated ,c.channel_partner_flag , c.fund_investor_type , m8.description as "fundInvestorTypeFromMaster", c.valuation_class, CASE WHEN c.valuation_class = 1 THEN 'Minicorn' WHEN c.valuation_class = 2 THEN 'Soonicorn' WHEN c.valuation_class = 3 THEN 'Unicorn' WHEN c.valuation_class = 4 THEN 'Decacorn' ELSE '' END as "valuationClassFromCode", c.year_of_valuation , c.digital_native_company , # binary flag
c.family_office_type , c.listing_date , c.updated_at , c.created_at from company c 
left join master m1 on c.companytypeid = m1.masterid  
left join master m2 on c.fundstatus = m2.masterid  
left join master m3 on c.companystatusid = m3.masterid 
left join country c2 on c2.countryid = c.countryid 
left join state s on s.stateid = c.stateid 
left join master m4 on c.directoryfeature = m4.masterid 
left join industry i on i.industry_id = c.industry_id 
left join sub_industry si on si.sub_industry_id = c.sub_industry_id 
left join master m9 on m9.masterId = c.stage_investment 
left join `global` g on g.globalid = c.`global` 
left join continent c3 on c3.continentid = c.continent 
left join india_sub_continent isc on isc.indiasubcontinentid = c.subcontinent 
left join master m5 on m5.masterid  = c.funding_status  
left join master m6 on m6.masterid = c.investor_type 
left join vccedge_ic vi on vi.vccedge_ic_id = c.vccedge_ic 
left join master m7 on m7.masterid = c.primary_exchange 
left join master m8 on m8.masterid  = c.fund_investor_type 
left join industry_group ig on ig.industry_group_id = c.industry_group_id ;
