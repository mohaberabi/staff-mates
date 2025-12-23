package com.erabigroup.erabigroupstaffmate.hardcoder

import com.erabigroupstaffmate.modelhub.BusinessData
import com.erabigroupstaffmate.modelhub.StaffModel

private const val storageBucket =
    "https://firebasestorage.googleapis.com/v0/b/erabigroupstaffmate.firebasestorage.app/o/staff_meta_data%2F"

class HardCodedStaffFactory() {
    fun create(businessData: BusinessData): List<StaffModel> {

        val base = baseHardCodedStaff(businessData = businessData)
        val mahmoudAbdelRazek = base.copy(
            id = "01140368474",
            fullName = "Mahmoud Ahmed Mahmoud AbdelRazik",
            legalName = "محمود احمد محمود عبدالرازق",
            profilePicUrl = bucketUrl("MahmoudAhmedMahmoudAbdelRazik%2Fpic.jpeg?alt=media&token=68eaa20c-e462-43cf-91bb-31a5b62199e8"),
            frontIdUrl = bucketUrl("MahmoudAhmedMahmoudAbdelRazik%2Ffront.jpeg?alt=media&token=f02a43af-87fd-4381-99be-85240a824033"),
            backIdUrl = bucketUrl("MahmoudAhmedMahmoudAbdelRazik%2Ffront.jpeg?alt=media&token=f02a43af-87fd-4381-99be-85240a824033"),
            title = "Branch Manager",
            baseSalary = 0.0,
            joinDate = "04-2025"
        )
        val abdelWahab = base.copy(
            id = "01110178823",
            fullName = "AbdelWahab Safwat AbdelWahab",
            legalName = "عبد الوهاب صفوت عبد الوهاب",
            profilePicUrl = bucketUrl("AbdelWahabSafwatAbdelWahab%2Fpic.jpeg?alt=media&token=0a2edcfa-e907-46db-ac16-bdc055d47ed2"),
            frontIdUrl = bucketUrl("AbdelWahabSafwatAbdelWahab%2Ffront.jpeg?alt=media&token=98e18371-9ede-49ad-b6eb-0dc15bc7831d"),
            backIdUrl = bucketUrl("AbdelWahabSafwatAbdelWahab%2Fback.jpeg?alt=media&token=12c700bd-2ac0-40ff-9b2c-a8757e608738"),
            title = "Chef Assistant 1",
            baseSalary = 8500.0,
            joinDate = "12-2025"
        )
        val mahmoudGamal = base.copy(
            id = "01142554945",
            fullName = "Mahmoud Gamal Mohamed",
            legalName = "محمود جمال محمد",
            profilePicUrl = bucketUrl("MahmoudGamalMohamedAhmed%2Fpic.jpeg?alt=media&token=1d683e19-362f-4e39-9aa5-caa79fda1231"),
            frontIdUrl = bucketUrl("MahmoudGamalMohamedAhmed%2Ffront.jpeg?alt=media&token=c689c942-b2f6-45bb-baf8-ee1f96ee3a67"),
            backIdUrl = bucketUrl("MahmoudGamalMohamedAhmed%2Fback.jpeg?alt=media&token=d86f05bf-ed3a-45b6-a9b0-1357d4a5c108"),
            title = "Branch Chef",
            baseSalary = 10500.0,
            joinDate = "12-2025"
        )
        val fahmi = base.copy(
            id = "01111895318",
            fullName = "Fahmi AlSayed Fahmi Omran",
            legalName = "فهمي السيد فهمي ",
            profilePicUrl = bucketUrl("FahmiAlSayedFahmiOmran%2Fpic.jpeg?alt=media&token=6b0465ff-a4f8-4786-a78f-441500598725"),
            frontIdUrl = bucketUrl("FahmiAlSayedFahmiOmran%2Ffront.jpeg?alt=media&token=9f1eac7d-425c-48c5-a40c-8f97250554bc"),
            backIdUrl = bucketUrl("FahmiAlSayedFahmiOmran%2Fback.jpeg?alt=media&token=e74e1e91-fd99-4470-ac6d-cbfb0244b44e"),
            title = "Branch Chef",
            baseSalary = 10500.0,
            joinDate = "12-2025"
        )
        val mohamedEhab = base.copy(
            id = "01017867314",
            fullName = "Mohamed Ehab Ahmed",
            legalName = "محمد ايهاب احمد",
            profilePicUrl = bucketUrl("MohamedEhabAhmedAli%2Fpic.jpeg?alt=media&token=589c6978-b7a3-45ee-9071-61234e7d6b51"),
            frontIdUrl = bucketUrl("MohamedEhabAhmedAli%2Ffront.jpeg?alt=media&token=423c3d51-68fb-46c6-b0ed-65f3e3bb5199"),
            backIdUrl = bucketUrl("MohamedEhabAhmedAli%2Fback.jpeg?alt=media&token=42f6345c-292a-4678-8998-29e9f47f2873"),
            title = "Chef Assistant 1",
            baseSalary = 7500.0,
            joinDate = "04-2025"
        )
        val mohamedAhmedAli = base.copy(
            id = "01011043756",
            fullName = "Mohamed Ahmed Ali",
            legalName = "محمد احمد علي",
            profilePicUrl = bucketUrl("MohamedAhmedAliAhmed%2FWhatsApp%20Image%202025-07-30%20at%207.35.38%20PM.jpeg?alt=media&token=1e953b9a-01e6-47ae-ac3c-0d6e6b20e922"),
            frontIdUrl = bucketUrl("MohamedAhmedAliAhmed%2FWhatsApp%20Image%202025-07-30%20at%207.35.38%20PM-2.jpeg?alt=media&token=e9344f47-0255-4923-ae1a-86ca7424df5e"),
            backIdUrl = bucketUrl("MohamedAhmedAliAhmed%2FWhatsApp%20Image%202025-07-30%20at%207.35.39%20PM.jpeg?alt=media&token=e1eb473e-1fb7-44a8-ba0e-dc4ff3757c75"),
            title = "Chef Assistant 1",
            baseSalary = 7500.0,
            joinDate = "04-2025"
        )
        val mahmoudKhaled = base.copy(
            id = "01141975161",
            fullName = "Mahmoud Khaled Abdullah",
            legalName = "محمود خالد عبد الله",
            profilePicUrl = bucketUrl("MahmoudKhaledAbdullah%2Fpic.jpeg?alt=media&token=d5188316-3cbe-42b5-8e65-bca73b0180cf"),
            frontIdUrl = bucketUrl("MahmoudKhaledAbdullah%2Ffront.JPG?alt=media&token=022ec8ec-0950-4a9e-bf9f-cf6345de22ca"),
            backIdUrl = bucketUrl("MahmoudKhaledAbdullah%2Fback.JPG?alt=media&token=ca579ee0-56fd-4cfe-8f5b-e6cc96f382b7"),
            title = "Cash Supervisor",
            baseSalary = 7500.0,
            joinDate = "04-2025",
        )
        val omarMaleh = base.copy(
            id = "01012766785",
            fullName = "Omar Ahmed Almaleh",
            legalName = "عمر احمد يوسف المالح",
            profilePicUrl = bucketUrl("OmarAhmedYoussefAlMaleh%2Fpic.jpeg?alt=media&token=93a03596-2e10-4116-9d83-92c2b1197140"),
            frontIdUrl = bucketUrl("OmarAhmedYoussefAlMaleh%2Ffront.jpeg?alt=media&token=c5f129de-819f-412b-864a-a5b5ec785f3c"),
            backIdUrl = bucketUrl("OmarAhmedYoussefAlMaleh%2Fback.jpeg?alt=media&token=050ec460-8616-40e0-b47b-e262568d8c5c"),
            title = "Waiter 1",
            baseSalary = 5500.0,
            joinDate = "06-2025",
        )
        val mostafaMamdouh = base.copy(
            id = "01144560068",
            fullName = "Mostafa Mamdouh",
            legalName = "مصطفي ممدوح احمد",
            profilePicUrl = bucketUrl("MostafaMamdouhAhmed%2Fpic.jpeg?alt=media&token=9076b4ea-03a8-4fba-a99a-3344ba699620"),
            frontIdUrl = bucketUrl("MostafaMamdouhAhmed%2Ffront.jpeg?alt=media&token=4120ced5-f33f-473f-aa40-a35961f31178"),
            backIdUrl = bucketUrl("MostafaMamdouhAhmed%2Fback.jpeg?alt=media&token=427dbafa-56a8-4347-99f6-e5610cba93b6"),
            title = "Cashier 1",
            baseSalary = 6000.0,
            joinDate = "07-2025",
        )
        val mohamedKhaled = base.copy(
            id = "01115138477",
            fullName = "Mohamed Khaled",
            legalName = "محمد خالد شوقي ",
            profilePicUrl = bucketUrl("MohamedKhaledShawkiMohamedMahmoud%2Fpic.jpeg?alt=media&token=7084b81d-8c2e-4039-9169-156b2b408b7e"),
            frontIdUrl = bucketUrl("MohamedKhaledShawkiMohamedMahmoud%2Ffront.jpeg?alt=media&token=eb260900-d471-4f79-87e8-e23897e180cd"),
            backIdUrl = bucketUrl("MohamedKhaledShawkiMohamedMahmoud%2Fback.jpeg?alt=media&token=e96ac1c1-0428-4007-b3d5-6892f2d74aa9"),
            title = "Office Boy",
            baseSalary = 5000.0,
            joinDate = "07-2025",
        )
        val mohamedMahmoud = base.copy(
            id = "01151699161",
            fullName = "Mohamed Mahmoud",
            legalName = "محمد محمود محمد ",
            profilePicUrl = bucketUrl("MohamedMahmouadMaohamedAli%2Fpic.jpeg?alt=media&token=efc2ca63-6432-4c34-a3c7-ed53cec5ab17"),
            frontIdUrl = bucketUrl("MohamedMahmouadMaohamedAli%2Ffront.jpeg?alt=media&token=c254cdf0-2bbf-4209-bfc3-25eaef6d2e0c"),
            backIdUrl = bucketUrl("MohamedMahmouadMaohamedAli%2Fback.jpeg?alt=media&token=e8bafac0-ea86-40ed-ab46-07152dad5a1f"),
            title = "Waiter 1",
            baseSalary = 5500.0,
            joinDate = "07-2025",
        )

        val youssefMohamed = base.copy(
            id = "01122328813",
            fullName = "Youssef Motalgah",
            legalName = "يوسف محمد محمود",
            profilePicUrl = bucketUrl("YousefMotalgah%2Fpic.jpeg?alt=media&token=13b574a1-1ee3-48d2-bb84-e11bbb3091de"),
            frontIdUrl = bucketUrl("YousefMotalgah%2Ffrontback.jpeg?alt=media&token=cbb986ed-d3c9-4535-a668-f6ce3eb94d8a"),
            backIdUrl = bucketUrl("YousefMotalgah%2Ffrontback.jpeg?alt=media&token=cbb986ed-d3c9-4535-a668-f6ce3eb94d8a"),
            title = "Dine-in Supervisor",
            baseSalary = 6000.0,
            joinDate = "05-2025",
        )

        val omarAbdelAzeez = base.copy(
            id = "01029487964",
            fullName = "Omar AbdelAzeez Khatab",
            legalName = "عمر عبد العزيز خطاب",
            profilePicUrl = bucketUrl("OmarAbdelAzeez%2Fpic.JPG?alt=media&token=187a0c8e-f671-41d7-8f19-2149a4acd793"),
            frontIdUrl = bucketUrl("OmarAbdelAzeez%2Ffront.JPG?alt=media&token=896dbf6a-b303-4384-ba88-ccefd0292492"),
            backIdUrl = bucketUrl("OmarAbdelAzeez%2Fback.JPG?alt=media&token=c669a348-9607-4de3-ae7c-77231f784774"),
            title = "Waiter-1",
            baseSalary = 5500.0,
            joinDate = "08-2025",
        )
        val omarMohamed = base.copy(
            id = "01013983953",
            fullName = "Omar Mohamed AbdelAzeem",
            legalName = "عمر محمد عبد العظيم",
            profilePicUrl = bucketUrl("OmarMohamedAbdelAzeem%2Fpic.JPG?alt=media&token=46bef0db-9e4d-4ff1-bbe8-ad8593d3f47c"),
            frontIdUrl = bucketUrl("OmarMohamedAbdelAzeem%2Ffront.JPG?alt=media&token=ac49bb8c-d598-4e10-b992-69b9abd803a0"),
            backIdUrl = bucketUrl("OmarMohamedAbdelAzeem%2Fback.JPG?alt=media&token=84934119-2302-45dc-8999-0d6ecdb91019"),
            title = "Cash Supervisor",
            baseSalary = 7500.0,
            joinDate = "04-2025",
        )
        return listOf(
            mahmoudAbdelRazek,
            abdelWahab,
            mahmoudGamal,
            fahmi,
            mohamedEhab,
            mohamedAhmedAli,
            mahmoudKhaled,
            omarMaleh,
            mostafaMamdouh,
            mohamedKhaled,
            mohamedMahmoud,
            youssefMohamed,
            omarAbdelAzeez,
            omarMohamed,
        )

    }


    private fun baseHardCodedStaff(
        businessData: BusinessData,
    ) = StaffModel(
        shiftHrs = 9,
        baseSalary = 0.0,
        vacationDays = 4,
        branchId = businessData.branchId,
        branchName = businessData.branchName,
        chainId = businessData.chainId,
        chainName = businessData.chainName,
        id = "",
        legalName = "",
        fullName = "",
        profilePicUrl = "",
        frontIdUrl = "",
        backIdUrl = "",
        title = "",
        isActive = true,
        joinDate = "",
    )
}

private fun bucketUrl(path: String) = buildString {
    append(storageBucket)
    append(path)
}