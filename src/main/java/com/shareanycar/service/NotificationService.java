package com.shareanycar.service;

import javax.inject.Inject;

import com.shareanycar.config.AppConfig;
import com.shareanycar.model.Booking;
import com.shareanycar.model.User;

public class NotificationService {

	@Inject
	public UserService userService;

	@Inject
	public AppConfig appConfig;

	public void notifyActivateAccount(Long userId) {
		User user = userService.findById(userId);
		System.out.println(appConfig.getServiceUrl() + "user/activate/" + user.getActivationToken());
	}

	public void notifyAccountActivated(Long userId) {
		User user = userService.findById(userId);
		System.out.print("account has been activated:" + user.getEmail());
	}

	public void notifyBookingConfirmed(Booking booking) {
		System.out.println("booking confirmed: " + booking.toString());
	}

	public void notifyBookingCanceled(Booking booking) {
		System.out.println("booking canceled: " + booking.toString());

	}

	public void notifyCarBooked(Booking booking) {
		System.out.println("car booked: " + booking.toString());
	}
}
