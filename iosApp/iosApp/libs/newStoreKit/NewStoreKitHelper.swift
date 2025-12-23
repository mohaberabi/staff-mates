//
// Created by Mohab Erabi on 30/08/2025.
//

import Foundation
import StoreKit
import UIKit

// we need to make it as [@objc ] to make kotlin understand it
//@escaping means this function is asynchronous function . means this call back
// does not need to be called immediately and might be called after a while in async manner

// i left this comments even as code is very simple but it's my first time i write a swift code
@objc public class NewStoreKitHelper: NSObject {
    @objc public static func presentManageSubscription(completion: @escaping (NSError?) -> Void) {
        // if device is at least using ios 15

        if #available(iOS 15.0, *) {
            guard let scene = UIApplication.shared.connectedScenes
                .compactMap({ $0 as? UIWindowScene })
                .first(where: { $0.activationState == .foregroundActive })
            else {

                let error = NSError(
                    domain: "NewStoreKitHelper",
                    code: 1,
                    userInfo: [NSLocalizedDescriptionKey: "No active scene"]
                )

                completion(error)
                return
            }
            Task {
                try? await AppStore.showManageSubscriptions(in: scene); completion(nil)
            }
        } else {
            UIApplication.shared.open(
                URL(string: "https://apps.apple.com/account/subscriptions")!,
                options: [:]
            ) { _ in
                completion(nil)
            }
        }
    }
}