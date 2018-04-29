package com.kthdv.training_point.services.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kthdv.training_point.constant.HeaderConstant;
import com.kthdv.training_point.utils.HttpPostRequestBuilder;
import org.springframework.web.client.RestTemplate;

public class FCMService {
    public static final String HEADER = HeaderConstant.KEY_VALUE_PREFIX + "AAAA_GyqTnE:APA91bGQtbR4eq6gLvaS9BhM1edi78VbzvCGeh_ETOGDsPlCNlkiBjW" +
            "-lFrz1SfGPgsGBPIKgVj2wFZ7nPfxrFtzLP3oQMXoOfAkJwbxf0plOJrLHgOdNgQFthrRy6Lg8o4-fhOumn8u";

    public static final String NOTIFICATION_URL = "fcm.googleapis.com/fcm/send";
    public static final String CLOUD_TOKEN =
            "ce8IeLg6vhY:APA91bH3a0BVnb5HxeEeVJ_HTeOStFWVhA9KliWaa4P6jZ_3LDRvqVy4CPkRC3kv3NSRCv_xglIvndwl60nsAiYWY475DiteW_0c8Hr7H_d06XZASPCssztFEPnekFglOBKl5hnbfkZU";
    public static NotificationResponse sendNotification(RestTemplate restTemplate, String cloudToken, Object notification) {
        if (notification == null || cloudToken == null || restTemplate == null) {
            return null;
        }

        Payload payload = new Payload();
        payload.setData(notification);
        payload.setTo(CLOUD_TOKEN);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new HttpPostRequestBuilder(restTemplate)
                    .withProtocol(HttpPostRequestBuilder.HTTPS)
                    .withUrl(NOTIFICATION_URL)
                    .addToHeader(HeaderConstant.AUTHORIZATION, HEADER)
                    .setJsonBody(objectMapper.writeValueAsString(payload))
                    .execute(NotificationResponse.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

//    public static DeviceGroupsResponse createOrAddNewUserDevice(RestTemplate restTemplate,
//                                                                UserFCMTokenRespository userFCMTokenRespository,
//                                                                UserFCMToken userFCMToken) {
//        DeviceGroupsResponse result = new DeviceGroupsResponse();
//        UserFCMToken userFCMTokenFound = userFCMTokenRespository.findByDeviceID(userFCMToken.getDeviceID());
//        NotificationKeyResponse currentNotificationKey = getDeviceGroupFCMToken(restTemplate, userFCMToken.getUserID());
//        NotificationKeyResponse notificationKeyResponse;
//        if (userFCMTokenFound == null) {
//            if (currentNotificationKey.getNotification_key() == null) {
//                //create new device group for this user
//                DeviceGroupsBody createNewDeviceGroupsBody = new DeviceGroupsBody(DeviceGroupsBody.CREATE,
//                        userFCMToken.getUserID(),
//                        userFCMToken.getFcmToken());
//                notificationKeyResponse = sendDeviceGroupRequest(restTemplate, createNewDeviceGroupsBody);
//            } else {
//                //add new device to user's device group
//                DeviceGroupsBody addDeviceToGroupBody = new DeviceGroupsBody(DeviceGroupsBody.ADD,
//                        userFCMToken.getUserID(),
//                        currentNotificationKey.getNotification_key(),
//                        userFCMToken.getFcmToken());
//                notificationKeyResponse = sendDeviceGroupRequest(restTemplate, addDeviceToGroupBody);
//            }
//        } else {
//            if (!userFCMTokenFound.getUserID().equals(userFCMToken.getUserID())) {
//                //previous user on this device not logout -> FORCE LOG OUT this user
//                NotificationKeyResponse forceLogoutUserNotificationKey =
//                        removeDevice(restTemplate, userFCMTokenRespository, userFCMTokenFound);
//                result.setForceLogoutUserID(userFCMTokenFound.getUserID());
//                result.setGroupFCMTokenOfForceLogoutUser(forceLogoutUserNotificationKey.getNotification_key());
//
//                DeviceGroupsBody addDeviceToGroupBody = new DeviceGroupsBody(DeviceGroupsBody.ADD,
//                        userFCMToken.getUserID(),
//                        currentNotificationKey.getNotification_key(),
//                        userFCMToken.getFcmToken());
//                notificationKeyResponse = sendDeviceGroupRequest(restTemplate, addDeviceToGroupBody);
//            } else {
//                if (userFCMTokenFound.getFcmToken().equals(userFCMToken.getFcmToken())) {
//                    notificationKeyResponse = currentNotificationKey;
//                } else {
//                    //fcm token of user device is refreshed, remove old fcm token from user's device group and add new one
//                    DeviceGroupsBody removeDeviceToGroupBody = new DeviceGroupsBody(DeviceGroupsBody.REMOVE,
//                            userFCMToken.getUserID(),
//                            currentNotificationKey.getNotification_key(),
//                            userFCMTokenFound.getFcmToken());
//                    sendDeviceGroupRequest(restTemplate, removeDeviceToGroupBody);
//                    DeviceGroupsBody addDeviceToGroupBody = new DeviceGroupsBody(DeviceGroupsBody.ADD,
//                            userFCMToken.getUserID(),
//                            currentNotificationKey.getNotification_key(),
//                            userFCMToken.getFcmToken());
//                    notificationKeyResponse = sendDeviceGroupRequest(restTemplate, addDeviceToGroupBody);
//                }
//            }
//        }
//        result.setGroupFCMToken(notificationKeyResponse.getNotification_key());
//        result.setUserFCMToken(userFCMToken);
//        return result;
//    }
//
//    public static NotificationKeyResponse removeDevice(RestTemplate restTemplate,
//                                                       UserFCMTokenRespository userFCMTokenRespository,
//                                                       UserFCMToken userFCMToken) {
//        NotificationKeyResponse currentNotificationKey = getDeviceGroupFCMToken(restTemplate, userFCMToken.getUserID());
//        if (currentNotificationKey.getNotification_key() == null) {
//            return currentNotificationKey;
//        }
//        DeviceGroupsBody removeDeviceToGroupBody = new DeviceGroupsBody(DeviceGroupsBody.REMOVE,
//                userFCMToken.getUserID(),
//                currentNotificationKey.getNotification_key(),
//                userFCMToken.getFcmToken());
//        NotificationKeyResponse notificationKeyResponse = sendDeviceGroupRequest(restTemplate, removeDeviceToGroupBody);
//        int userDeviceCount = userFCMTokenRespository.getUserDeviceCount(userFCMToken.getUserID());
//        if (userDeviceCount == 1) {
//            notificationKeyResponse.setNotification_key(null);
//        }
//        return notificationKeyResponse;
//    }
//
//    private static NotificationKeyResponse sendDeviceGroupRequest(RestTemplate restTemplate, DeviceGroupsBody deviceGroupsBody) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return new HttpPostRequestBuilder(restTemplate)
//                    .withProtocol(HttpPostRequestBuilder.HTTPS)
//                    .withUrl(DEVICE_GROUPS_URL)
//                    .addToHeader(HeaderConstant.AUTHORIZATION, HEADER)
//                    .addToHeader(HeaderConstant.PROJECT_ID, SENDER_ID)
//                    .setJsonBody(objectMapper.writeValueAsString(deviceGroupsBody))
//                    .execute(NotificationKeyResponse.class);
//        } catch (Exception e) {
//            NotificationKeyResponse notificationKeyResponse = getDeviceGroupFCMToken(restTemplate, deviceGroupsBody.getNotification_key_name());
//            return new NotificationKeyResponse(deviceGroupsBody.getNotification_key());
//        }
//    }
//
//    private static NotificationKeyResponse getDeviceGroupFCMToken(RestTemplate restTemplate, String notificationKeyName) {
//        try {
//            return new HttpGetRequestBuilder(restTemplate)
//                    .withProtocol(HttpGetRequestBuilder.HTTPS)
//                    .withUrl(DEVICE_GROUPS_URL)
//                    .addToHeader(HeaderConstant.AUTHORIZATION, HEADER)
//                    .addToHeader(HeaderConstant.PROJECT_ID, SENDER_ID)
//                    .addToHeader(HeaderConstant.CONTENT_TYPE, HeaderConstant.APPLICATION_JSON)
//                    .withParam(DeviceGroupsBody.NOTIFICATION_KEY_NAME, notificationKeyName)
//                    .execute(NotificationKeyResponse.class);
//        } catch (Exception e) {
//            return new NotificationKeyResponse();
//        }
//    }
}
