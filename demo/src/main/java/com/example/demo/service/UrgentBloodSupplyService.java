package com.example.demo.service;

import com.example.demo.grpc.UrgentBloodSupplyGrpc;
import com.example.demo.grpc.UrgentBloodSupplyOuterClass;
import com.example.demo.model.BloodBank;
import com.example.demo.dto.BloodTypeDTO;
import com.example.demo.model.BloodSupply;
import com.example.demo.repository.BloodSupplyRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;


@GrpcService
public class UrgentBloodSupplyService extends UrgentBloodSupplyGrpc.UrgentBloodSupplyImplBase {

    private final BloodSupplyRepository BloodSupplyRepository;
    private  final  BloodSupplyService BloodSupplyService;
    @Autowired
    public UrgentBloodSupplyService(BloodSupplyRepository bloodSupplyRepository, com.example.demo.service.BloodSupplyService bloodSupplyService) {
        this.BloodSupplyRepository = bloodSupplyRepository;
        this.BloodSupplyService = bloodSupplyService;
    }

    @Override
    public void orderBloodUrgently(UrgentBloodSupplyOuterClass.Request request, StreamObserver<UrgentBloodSupplyOuterClass.Response> responseObserver) {

        BloodTypeDTO bloodType = convertToBloodType(request.getBloodType());
        int bloodQuantity = request.getQuantity();

        UrgentBloodSupplyOuterClass.Response.Builder response = UrgentBloodSupplyOuterClass.Response.newBuilder();

//        if (bloodType == BloodType.Apos){
//            response.setBloodBankName("ZAHTJEV POSLAT").setBloodType(bloodType.toString()).setQuantity(bloodQuantity);
//        }else {
//            response.setBloodBankName("KRV NIJE ISPORUCENA").setBloodType(bloodType.toString()).setQuantity(bloodQuantity);
//        }

        BloodBank bloodBank = checkBloodTypeAndQuantity(bloodType,  bloodQuantity);

        if(bloodBank!=null){
            String bloodBankName = bloodBank.getName();
            response.setBloodBankName(bloodBankName).setBloodType(bloodType.toString()).setQuantity(bloodQuantity);
            BloodSupplyService.reduceForDeliveredQuantity(bloodBankName,bloodQuantity,bloodType);

        }else{
            response.setBloodBankName("").setBloodType(bloodType.toString()).setQuantity(bloodQuantity);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public BloodBank checkBloodTypeAndQuantity(BloodTypeDTO bloodType, int quantity){

        BloodSupply bs =  BloodSupplyRepository.findByBloodType(bloodType);

        if (bs == null) {
            return null;
        }else{
            if (bs.getQuantity() >= quantity)
                return bs.getBloodBank();
            else
                return null;
        }

    }

    public BloodTypeDTO convertToBloodType(String bloodType){
        if(bloodType.equals("Apos"))
            return BloodTypeDTO.Apos;
        else if (bloodType.equals("Aneg"))
            return  BloodTypeDTO.Aneg;
        else if (bloodType.equals("Bpos"))
            return BloodTypeDTO.Bpos;
        else if (bloodType.equals("Bneg"))
            return BloodTypeDTO.Bneg;
        else if (bloodType.equals("ABpos"))
            return BloodTypeDTO.ABpos;
        else if (bloodType.equals("ABneg"))
            return BloodTypeDTO.ABneg;
        else if (bloodType.equals("Opos"))
            return BloodTypeDTO.Opos;
        else
            return  BloodTypeDTO.Oneg;
    }
}
