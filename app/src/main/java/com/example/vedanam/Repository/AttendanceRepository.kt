package com.example.vedanam.Repository

import com.example.vedanam.MainNetwork
import com.example.vedanam.Model.AttendanceInputModel
import com.example.vedanam.Model.AttendanceModel

class AttendanceRepository(val network:MainNetwork) {

        suspend fun refreshAttendance(request: AttendanceInputModel):List<AttendanceModel>{
                val result:List<AttendanceModel>
                try{
                 result= network.fetchAttendance(request)
                }
                catch(cause:Throwable){
                        throw  AttendanceRefreshError("Unable to fetch atendance",cause)

                }

                return result

        }


}


class AttendanceRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)