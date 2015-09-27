ALTER TABLE client
ADD CONSTRAINT FOREIGN KEY (`id_grant`) REFERENCES `client_grant` (`id`);

ALTER TABLE `client_grant`
ADD CONSTRAINT FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

ALTER TABLE `order`
ADD CONSTRAINT FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
ADD CONSTRAINT FOREIGN KEY (`id_address`) REFERENCES `order_address` (`id`);

ALTER TABLE `order_address`
ADD CONSTRAINT FOREIGN KEY (`id_order`) REFERENCES `order` (`id`);

ALTER TABLE `review`
ADD CONSTRAINT FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

