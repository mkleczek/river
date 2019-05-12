/*
 * Copyright 2019 Michal Kleczek (michal@kleczek.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Michal Kleczek ({@literal michal@kleczek.org})
 */
module net.codespaces.river.outrigger.dl
{
    exports org.apache.river.outrigger.dl to net.codespaces.river.outrigger;
    
    requires transitive net.codespaces.river.services.api;
    requires net.codespaces.river.base;
    requires net.codespaces.river.landlord;
    
    requires java.logging;
}